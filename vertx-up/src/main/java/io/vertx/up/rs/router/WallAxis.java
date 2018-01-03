package io.vertx.up.rs.router;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.ChainAuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.Orders;
import io.vertx.up.rs.Axis;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Values;

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
            try {
                // 1. Build Handler
                final AuthHandler handler = create(cliffes);
                // 2. Path/Order to set Router
                if (null != handler) {
                    router.route(path).order(Orders.SECURE).handler(handler);
                }
            } catch (final Throwable ex) {
                ex.printStackTrace();
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
            for (final Cliff cliff : cliffes) {
                // Append
                final AuthHandler handler = cliff.<AuthHandler>getHandler().get();
                if (null != handler) {
                    chain.append(handler);
                }
            }
            resultHandler = chain;
        } else {
            // 1 = handler
            if (!cliffes.isEmpty()) {
                final Cliff cliff = cliffes.iterator().next();
                resultHandler = cliff.<AuthHandler>getHandler().get();
            }
        }
        return resultHandler;
    }
}
