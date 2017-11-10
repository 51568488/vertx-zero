package io.vertx.up.rs.config;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.config.example.ED;
import io.vertx.up.rs.config.example.ED1;
import org.junit.Test;
import top.UnitBase;

import java.util.Set;

public class EndPointTc extends UnitBase {

    @Test
    public void testEndPoint(final TestContext context) {
        final Set<Event> all = new ConcurrentHashSet<>();
        all.addAll(extractor().extract(ED1.class));
        all.addAll(extractor().extract(ED.class));
        for (final Event event : all) {
            getLogger().info("[TEST] Extract event: {0}.", event);
        }
    }
}
