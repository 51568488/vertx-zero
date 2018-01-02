package io.vertx.up.atom.secure;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.secure.Secreter;
import io.vertx.up.tool.Compare;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Secure class container for special class extraction.
 * Scanned ( Metadata ) for each @Wall.
 */
public class Cliff implements Serializable, Comparable<Cliff> {
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
     * User class
     */
    private Class<?> user;
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
    /**
     * Reference for secreter
     */
    private Secreter secreter;

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public void setConfig(final JsonObject config) {
        this.config = config;
    }

    public WallType getType() {
        return this.type;
    }

    public void setType(final WallType type) {
        this.type = type;
    }

    public Class<?> getHandler() {
        return this.handler;
    }

    public void setHandler(final Class<?> handler) {
        this.handler = handler;
    }

    public Class<?> getProvider() {
        return this.provider;
    }

    public void setProvider(final Class<?> provider) {
        this.provider = provider;
    }

    public Object getProxy() {
        return this.proxy;
    }

    public void setProxy(final Object proxy) {
        this.proxy = proxy;
    }

    public Method getAuthenticate() {
        return this.authenticate;
    }

    public void setAuthenticate(final Method authenticate) {
        this.authenticate = authenticate;
    }

    public Method getAuthorize() {
        return this.authorize;
    }

    public void setAuthorize(final Method authorize) {
        this.authorize = authorize;
    }

    public Class<?> getUser() {
        return this.user;
    }

    public void setUser(final Class<?> user) {
        this.user = user;
    }

    public Secreter getSecreter() {
        return this.secreter;
    }

    public void setSecreter(final Secreter secreter) {
        this.secreter = secreter;
    }

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
    public int compareTo(final Cliff target) {
        return Compare.compareTo(this, target, (left, right) -> {
            // 1. Compare Path
            int result = Compare.compareTo(left.getPath(), right.getPath());
            if (0 == result) {
                // 2. Compare Order
                result = Compare.compareTo(left.getOrder(), right.getOrder());
            }
            return result;
        });
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
                ", user=" + this.user +
                '}';
    }
}
