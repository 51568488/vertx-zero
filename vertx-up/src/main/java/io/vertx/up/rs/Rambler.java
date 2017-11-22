package io.vertx.up.rs;

import io.vertx.zero.exception.UpException;

import java.lang.annotation.Annotation;

/**
 * Verification for epsilon
 */
public interface Rambler {

    void verify(Class<? extends Annotation> clazz,
                Class<?> type) throws UpException;
}
