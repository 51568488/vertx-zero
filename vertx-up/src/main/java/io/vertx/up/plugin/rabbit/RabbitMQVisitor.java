package io.vertx.up.plugin.rabbit;

import io.vertx.rabbitmq.RabbitMQOptions;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.Visitor;

public class RabbitMQVisitor implements Visitor<RabbitMQOptions> {

    @Override
    public RabbitMQOptions visit(final String... key)
            throws ZeroException {

        return null;
    }
}
