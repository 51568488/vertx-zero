package io.vertx.zero.tool.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

import java.util.Set;

public class PackTc extends UnitBase {

    @Test
    public void testScan(final TestContext context) {
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        for (final Class<?> clazz : clazzes) {
            // System.out.println(clazz);
        }
        context.assertNotNull(clazzes);
    }
}
