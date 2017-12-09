package io.vertx.up.kidd;

/**
 * Id implementation for identifier to front
 */
public interface Spy<T> {
    /**
     * Request processing
     *
     * @param request
     * @return
     */
    T in(T request);

    /**
     * Response processing
     *
     * @param response
     * @return
     */
    T out(T response);
}
