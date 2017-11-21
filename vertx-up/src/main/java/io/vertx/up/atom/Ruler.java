package io.vertx.up.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

import java.io.Serializable;

public class Ruler implements Serializable {

    private final String type;

    private final String message;

    private final JsonObject config;

    public static Ruler create(final JsonObject data) {
        return Fn.get(null, () -> new Ruler(data), data);
    }

    private Ruler(final JsonObject data) {
        this.type = data.getString("type");
        this.message = data.getString("message");
        this.config = data;
        this.config.remove("type");
        this.config.remove("message");
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }

    public JsonObject getConfig() {
        return this.config;
    }

    @Override
    public String toString() {
        return "Ruler{" +
                "type='" + this.type + '\'' +
                ", message='" + this.message + '\'' +
                ", config=" + this.config.encode() +
                '}';
    }
}
