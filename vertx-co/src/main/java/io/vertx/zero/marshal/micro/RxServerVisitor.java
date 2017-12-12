package io.vertx.zero.marshal.micro;

import io.vertx.up.eon.em.ServerType;

public class RxServerVisitor extends HttpServerVisitor {

    @Override
    protected ServerType getType() {
        return ServerType.RX;
    }
}
