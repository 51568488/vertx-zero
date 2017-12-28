package io.vertx.zero.marshal.equip;

import io.vertx.up.eon.em.ServerType;

public class RxServerVisitor extends HttpServerVisitor {

    @Override
    protected ServerType getType() {
        return ServerType.RX;
    }
}
