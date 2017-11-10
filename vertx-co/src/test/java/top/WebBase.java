package top;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.WebTestBase;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.Hub;
import io.vertx.up.rs.config.EndPointExtractor;
import io.vertx.up.rs.router.EventHub;
import io.vertx.up.rs.router.RouterHub;
import io.vertx.up.web.ZeroGrid;
import org.vie.util.Instance;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class WebBase extends WebTestBase {

    protected Event extract(final Class<?> clazz, final String name) {
        final Extractor<Set<Event>> extractor = Instance.singleton(EndPointExtractor.class);
        final Set<Event> events = extractor.extract(clazz);
        final List<Event> list = events.stream()
                .filter(item -> item.getAction().getName().equals(name))
                .collect(Collectors.toList());
        return list.get(0);
    }


    private static final ConcurrentMap<Integer, HttpServerOptions>
            SERVERS = ZeroGrid.getServerOptions();
    private static final ConcurrentMap<Integer, AtomicInteger>
            LOGS = new ConcurrentHashMap<Integer, AtomicInteger>() {
        {
            SERVERS.forEach((port, option) -> {
                put(port, new AtomicInteger(0));
            });
        }
    };

    protected void start() {
        /** 1.Get the default HttpServer Options **/
        SERVERS.forEach((port, option) -> {
            /** 4.Call router hub to mount commont **/
            Hub huber = Instance.singleton(RouterHub.class);
            huber.mount(this.router);
            /** 5.Call route hub to mount defined **/
            huber = Instance.singleton(EventHub.class);
            huber.mount(this.router);
        });
    }
}
