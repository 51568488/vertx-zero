package io.vertx.up.atom.secure;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.WallType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Secure class container for special class extraction.
 * Scanned ( Metadata ) for each @Wall.
 */
public class Cliff implements Serializable {
    /**
     * The wall path to be security limitation
     */
    private String path;
    /**
     * Current wall order
     */
    private int order;
    /**
     * Current config
     */
    private JsonObject config;
    /**
     * Current wall type
     */
    private WallType type;
    /**
     * AuthHandler class
     */
    private Class<?> handler;
    /**
     * Provider class
     */
    private Class<?> provider;
    /**
     * Proxy instance
     */
    private Object proxy;
    /**
     * 401: Authenticate method
     */
    private Method authenticate;
    /**
     * 403: Authorize method
     */
    private Method authorize;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliff)) {
            return false;
        }
        final Cliff wall = (Cliff) o;
        return this.order == wall.order &&
                Objects.equals(this.path, wall.path) &&
                this.type == wall.type &&
                Objects.equals(this.proxy, wall.proxy);
    }


    @Override
    public int hashCode() {

        return Objects.hash(this.path, this.order, this.type, this.proxy);
    }

    @Override
    public String toString() {
        return "Cliff{" +
                "path='" + this.path + '\'' +
                ", order=" + this.order +
                ", config=" + this.config +
                ", type=" + this.type +
                ", handler=" + this.handler +
                ", provider=" + this.provider +
                ", proxy=" + this.proxy +
                ", authenticate=" + this.authenticate +
                ", authorize=" + this.authorize +
                '}';
    }

}
