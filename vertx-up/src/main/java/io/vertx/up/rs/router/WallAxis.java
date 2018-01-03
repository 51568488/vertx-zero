package io.vertx.up.rs.router;

import io.reactivex.Observable;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.ChainAuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.Orders;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Axis;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.failure.AuthenticateEndurer;
import io.vertx.zero.eon.Values;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Secure mount
 */
public class WallAxis implements Axis<Router> {

    private transient final Vertx vertx;

    /**
     * Extract all walls that will be generated route.
     */
    private static final Set<Cliff> WALLS =
            ZeroAnno.getWalls();

    static {
        WALLS.forEach(wall -> {
            // Initialize cliff set
            if (!Pool.WALL_MAP.containsKey(wall.getPath())) {
                Pool.WALL_MAP.put(wall.getPath(), new TreeSet<>());
            }
            // Add cliff instance by path
            Pool.WALL_MAP.get(wall.getPath()).add(wall);
        });
    }

    public WallAxis(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void mount(final Router router) {
        // Session Global for Authorzation
        router.route("/*").order(Orders.SESSION).handler(
                SessionHandler.create(LocalSessionStore.create(this.vertx))
        );
        Pool.WALL_MAP.forEach((path, cliffes) -> {
            // 1. Build Handler
            final AuthHandler handler = create(cliffes);
            // 2. Path/Order to set Router
            if (null != handler) {
                router.route(path).order(Orders.SECURE).handler(handler)
                        .failureHandler(AuthenticateEndurer.create());
            }
        });
    }

    /**
     * Two mode for handler supported.
     *
     * @param cliffes
     * @return
     */
    private AuthHandler create(final Set<Cliff> cliffes) {
        AuthHandler resultHandler = null;
        if (Values.ONE < cliffes.size()) {
            // 1 < handler
            final ChainAuthHandler chain = ChainAuthHandler.create();
            Observable.fromIterable(cliffes)
                    .filter(Objects::nonNull)
                    .map(this::get)
                    .filter(Objects::nonNull)
                    .subscribe(chain::append);
            resultHandler = chain;
        } else {
            // 1 = handler
            if (!cliffes.isEmpty()) {
                final Cliff cliff = cliffes.iterator().next();
                resultHandler = get(cliff);
            }
        }
        return resultHandler;
    }

    private AuthHandler get(final Cliff cliff) {
        return Fn.getJvm(() -> {
            JsonObject config = cliff.getConfig();
            config = null == config ? new JsonObject() : config;
            final Object reference = cliff.getAuthorizer().getAuthenticate().invoke(cliff.getProxy(), config);
            return null == reference ? null : (AuthHandler) reference;
        }, cliff, cliff.getProxy(), cliff.getAuthorizer(), cliff.getAuthorizer().getAuthenticate());
    }
}
