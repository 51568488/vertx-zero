package org.vie.util.mirror;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.test.UnitBase;

import java.util.Set;

public class UnitScan extends UnitBase {
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
