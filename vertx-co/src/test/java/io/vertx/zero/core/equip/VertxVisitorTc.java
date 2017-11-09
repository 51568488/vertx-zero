package io.vertx.zero.core.equip;

import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import org.vie.exception.ZeroException;
import org.vie.util.Instance;
import top.UnitBase;

import java.util.concurrent.ConcurrentMap;

public class VertxVisitorTc extends UnitBase {

    @Test
    public void testVertx(final TestContext context) throws ZeroException {
        final UprightVisitor visitor
                = Instance.singleton(VertxVisitor.class);
        final ConcurrentMap<String, VertxOptions> options = visitor.visit();
        System.out.println(options);
    }
}
