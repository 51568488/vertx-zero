package io.vertx.up.tool.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.annotations.Agent;
import io.vertx.up.example.AnnoAgent;
import io.vertx.up.example.AnnoOne;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentMap;

public class AnnoTc extends ZeroBase {

    @Test
    public void testAnnos(final TestContext context) {
        final ConcurrentMap<String, Annotation> clazzes = Anno.get(AnnoOne.class);
        for (final String item : clazzes.keySet()) {
            System.out.println("key=" + item + ",value=" + clazzes.get(item));
        }
        context.assertEquals(2, clazzes.size());
    }

    @Test
    public void testAttrs(final TestContext context) {
        final String item = Anno.getAttribute(AnnoAgent.class, Agent.class, "group");
        context.assertEquals("_ZERO_", item);
    }
}
