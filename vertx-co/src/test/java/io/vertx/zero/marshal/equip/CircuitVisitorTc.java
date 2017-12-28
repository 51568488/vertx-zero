package io.vertx.zero.marshal.equip;

import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ArgumentException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.Visitor;
import org.junit.Test;

public class CircuitVisitorTc extends ZeroBase {

    final Visitor<CircuitBreakerOptions> visitor
            = Instance.singleton(CircuitVisitor.class);

    @Test(expected = ArgumentException.class)
    public void testVisit() throws ZeroException {
        final Visitor<CircuitBreakerOptions> visitor
                = Instance.singleton(CircuitVisitor.class);
        visitor.visit("");
    }

    @Test
    public void testData() throws ZeroException {
        this.visitor.visit();
    }
}
