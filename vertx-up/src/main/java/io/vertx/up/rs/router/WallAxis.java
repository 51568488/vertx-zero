package io.vertx.up.rs.router;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.ChainAuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.Orders;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Axis;
import io.vertx.up.secure.Secreter;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Values;

import java.util.Set;
import java.util.TreeSet;

/**
 * Secure mount
 */
public class WallAxis implements Axis<Router> {

    private static final Annal LOGGER = Annal.get(WallAxis.class);

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
        Pool.WALL_MAP.forEach((path, cliffes) -> {
            router.route("/*").handler(SessionHandler.create(LocalSessionStore.create(this.vertx)));
            // Mount router for chain
            if (Values.ONE < cliffes.size()) {
                // 1 < More than one : Chain Mode
                final ChainAuthHandler chain = ChainAuthHandler.create();
                for (final Cliff cliff : cliffes) {
                    // Append each
                    final AuthHandler handler = build(cliff);
                    if (null != handler) {
                        chain.append(handler);
                    }
                }
                router.route(path).order(Orders.SECURE)
                        .handler(chain);
            } else {
                // 1 Only one: Single Mode
                final AuthHandler handler = build(cliffes.iterator().next());
                if (null != handler) {
                    router.route(path).order(Orders.SECURE)
                            .handler(handler);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private AuthHandler build(final Cliff cliff) {
        final Secreter<RoutingContext, Vertx> secreter = cliff.getSecreter();
        return secreter.mount(cliff, this.vertx);
    }
}
