package io.vertx.up.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.up.annotations.Agent;
import io.vertx.up.annotations.Up;
import org.junit.Test;
import top.UnitBase;
import top.example.AnnoAgent;
import top.example.AnnoOne;

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
    public void testMark(final TestContext context) {
        final boolean isMark = Anno.isMark(AnnoOne.class, Up.class);
        context.assertTrue(isMark);
    }

    @Test
    public void testUnmark(final TestContext context) {
        final boolean isMark = Anno.isMark(AnnoOne.class, Agent.class);
        context.assertFalse(isMark);
    }

    @Test
    public void testAttrs(final TestContext context) {
        final String item = Anno.getAttribute(AnnoAgent.class, Agent.class, "group");
        context.assertEquals("_ZERO_", item);
    }
}
