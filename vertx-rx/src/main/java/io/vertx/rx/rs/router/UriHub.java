package io.vertx.rx.rs.router;

import io.vertx.reactivex.ext.web.Route;
import io.vertx.up.atom.Event;
import io.vertx.up.rs.router.Hub;

public class UriHub implements Hub<Route> {

    @Override
    public void mount(final Route route,
                      final Event event) {
        route.path(event.getPath())
                .method(event.getMethod())
                .order(event.getOrder());
    }
}
