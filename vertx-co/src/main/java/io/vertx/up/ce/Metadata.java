package io.vertx.up.ce;

import com.vie.cv.Values;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.impl.ConcurrentHashSet;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Set;

/**
 * Metadata to store jsr311 annotation information
 */
public class Metadata implements Serializable {
    /**
     * Used routine class
     */
    private Class<?> belongTo;
    /**
     * Http method information
     */
    private HttpMethod method;
    /**
     * Request path;
     */
    private String path;
    /**
     * Server Content
     */
    private final Set<MediaType> content = new ConcurrentHashSet<>();
    /**
     * Client Content
     */
    private final Set<MediaType> accept = new ConcurrentHashSet<>();
    /**
     * Order
     */
    private int order = Values.UNSET;
    /**
     * Whether use regular expression in path
     */
    private boolean regex = false;
    /**
     * Whether use normalized path
     */
    private boolean normalize = false;

    public Class<?> getBelongTo() {
        return this.belongTo;
    }

    public void setBelongTo(final Class<?> belongTo) {
        this.belongTo = belongTo;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(final HttpMethod method) {
        this.method = method;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Set<MediaType> getContent() {
        return this.content;
    }

    public Set<MediaType> getAccept() {
        return this.accept;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    public boolean isRegex() {
        return this.regex;
    }

    public void setRegex(final boolean regex) {
        this.regex = regex;
    }

    public boolean isNormalize() {
        return this.normalize;
    }

    public void setNormalize(final boolean normalize) {
        this.normalize = normalize;
    }
}
