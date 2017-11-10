package io.vertx.up.rs.router;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.up.cv.Orders;
import io.vertx.up.rs.Hub;

public class RouterHub implements Hub {

    @Override
    public void mount(final Router router) {
        // 1. Cookie, Body
        router.route()
                .order(Orders.COOKIE)
                .handler(CookieHandler.create());
        router.route()
                .order(Orders.BODY)
                .handler(BodyHandler.create());
        // 2. Session
    }
}
