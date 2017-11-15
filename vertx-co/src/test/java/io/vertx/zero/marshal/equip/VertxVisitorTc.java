package io.vertx.zero.marshal.equip;

import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class VertxVisitorTc extends ZeroBase {

    @Test
    public void testVertx(final TestContext context) throws ZeroException {
        final NodeVisitor visitor
                = Instance.singleton(VertxVisitor.class);
        final ConcurrentMap<String, VertxOptions> options = visitor.visit();
        System.out.println(options);
    }
}
