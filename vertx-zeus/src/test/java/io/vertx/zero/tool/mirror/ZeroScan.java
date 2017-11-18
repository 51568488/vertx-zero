package io.vertx.zero.tool.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.exception.WebException;
import org.junit.Test;
import org.tlk.exception.TestRequestException;

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

    @Test
    public void testScan() {
        final WebException error = new TestRequestException(getClass(),
                "Lang", "Detail");
        System.out.println(error);
    }
}
