package io.vertx.up.rs.router;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Axis;
import io.vertx.up.web.ZeroAnno;

import java.util.Set;

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

    public WallAxis(final Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void mount(final Router router) {
        // System.out.println(this.vertx);
    }
}
