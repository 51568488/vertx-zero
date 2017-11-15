package io.vertx.zero.infinite.rabbit;

import io.vertx.exception.ZeroException;
import io.vertx.rabbitmq.RabbitMQOptions;
import io.vertx.zero.core.Visitor;

public class RabbitMQVisitor implements Visitor<RabbitMQOptions> {

    @Override
    public RabbitMQOptions visit(final String... key)
            throws ZeroException {
        
        return null;
    }
}
