package top;

import io.vertx.ext.web.WebTestBase;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.EndPointExtractor;
import org.vie.util.Instance;

import java.util.List;
import java.util.Set;
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
}
