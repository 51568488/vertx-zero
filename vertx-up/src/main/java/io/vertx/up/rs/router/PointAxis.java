package io.vertx.up.rs.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.up.eon.Orders;
import io.vertx.up.micro.discovery.ServiceJet;
import io.vertx.up.rs.Axis;

/**
 * Router mounter for api gateway.
 */
public class PointAxis implements Axis<Router> {

    private transient final HttpServerOptions options;
    private transient final Vertx vertx;

    public PointAxis(final HttpServerOptions options,
                     final Vertx vertx) {
        this.options = options;
        this.vertx = vertx;
    }

    @Override
    public void mount(final Router router) {
        /** Breaker and Dispatch **/
        router.route("/*")
                .order(Orders.EVENT)
                .handler(ServiceJet
                        .create(this.options)
                        .connect(this.vertx)
                        .handle());
    }
}
