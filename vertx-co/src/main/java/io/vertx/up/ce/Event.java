package io.vertx.up.ce;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.cv.Orders;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Scanned Uri Event ( Metadata ) for each Endpoint.
 */
public class Event implements Serializable {
    /**
     * The uri address for current route
     */
    private String path;
    /**
     * order for current Event
     * ( Could not be modified in current version )
     */
    private final int order = Orders.EVENT;
    /**
     * consume mime
     */
    private Set<MediaType> consumes;
    /**
     * produce mime
     */
    private Set<MediaType> produces;
    /**
     * http method.
     */
    private HttpMethod method;
    /**
     * request action ( Will be calculated )
     */
    private Method action;
    /**
     * request action arguments ( Will be calculated )
     */
    private Object[] arguments;

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public int getOrder() {
        return this.order;
    }

    public Set<MediaType> getConsumes() {
        return this.consumes;
    }

    public void setConsumes(final Set<MediaType> consumes) {
        this.consumes = consumes;
    }

    public Set<MediaType> getProduces() {
        return this.produces;
    }

    public void setProduces(final Set<MediaType> produces) {
        this.produces = produces;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(final HttpMethod method) {
        this.method = method;
    }

    public Method getAction() {
        return this.action;
    }

    public void setAction(final Method action) {
        this.action = action;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public void setArguments(final Object[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Event event = (Event) o;

        if (!this.path.equals(event.path)) {
            return false;
        }
        if (this.method != event.method) {
            return false;
        }
        return this.action.equals(event.action);
    }

    @Override
    public int hashCode() {
        int result = this.path.hashCode();
        result = 31 * result + this.method.hashCode();
        result = 31 * result + this.action.hashCode();
        return result;
    }
}
