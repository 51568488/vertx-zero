package io.vertx.up.tool.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import org.junit.Test;

import java.util.Set;

public class ZeroScan extends ZeroBase {
    @Test
    public void testScan(final TestContext context) {
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        for (final Class<?> clazz : clazzes) {
            // System.out.println(clazz);
            System.out.println(clazz.getName());
        }
        context.assertNotNull(clazzes);
    }
}
