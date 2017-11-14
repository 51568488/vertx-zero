package org.vie.util.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.test.example.AnnoAgent;
import io.vertx.test.example.AnnoOne;
import io.vertx.up.annotations.Agent;
import org.junit.Test;
import top.test.UnitBase;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentMap;

public class AnnoTc extends UnitBase {

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
