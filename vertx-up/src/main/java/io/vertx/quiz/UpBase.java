package io.vertx.quiz;

import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.up.atom.Event;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.EventExtractor;
import io.vertx.up.tool.mirror.Instance;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.Set;

@RunWith(VertxUnitRunner.class)
public abstract class UpBase {

    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    protected Extractor<Set<Event>> extractor() {
        return Instance.singleton(EventExtractor.class);
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
