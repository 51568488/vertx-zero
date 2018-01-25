package io.vertx.up.aiki;

import io.vertx.up.tool.Jackson;

class Debug {

    static void monitor(final Object object) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\t\t[ ZERO Debug ] ---> Start \n");
        builder.append("\t\t[ ZERO Debug ] object = ").append(object).append("\n");
        if (null != object) {
            builder.append("\t\t[ ZERO Debug ] type = ").append(object.getClass()).append("\n");
            builder.append("\t\t[ ZERO Debug ] json = ").append(Jackson.serialize(object)).append("\n");
            builder.append("\t\t[ ZERO Debug ] toString = ").append(object.toString()).append("\n");
            builder.append("\t\t[ ZERO Debug ] hashCode = ").append(object.hashCode()).append("\n");
        }
        builder.append("\t\t[ ZERO Debug ] <--- End \n");
        System.err.println(builder.toString());
    }
}
