package io.vertx.up.secure.inquire;

import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Authorize;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.WallMethodMultiException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class PhylumAuth {

    private final transient Class<?> clazz;
    private final transient Annal logger;
    private final transient Method[] methods;

    public static PhylumAuth create(final Class<?> clazz) {
        return new PhylumAuth(clazz);
    }

    private PhylumAuth(final Class<?> clazz) {
        this.clazz = clazz;
        this.logger = Annal.get(clazz);
        this.methods = clazz.getDeclaredMethods();
    }

    public PhylumAuth verify() {
        // Duplicated Method checking
        Fn.flingUp(verifyMethod(this.methods, Authenticate.class), this.logger,
                WallMethodMultiException.class, getClass(),
                Authenticate.class.getSimpleName(), this.clazz.getName());
        Fn.flingUp(verifyMethod(this.methods, Authorize.class), this.logger,
                WallMethodMultiException.class, getClass(),
                Authorize.class.getSimpleName(), this.clazz.getName());
        return this;
    }

    public void mount(final Cliff reference) {
        /** Proxy **/
        reference.setProxy(Instance.instance(this.clazz));
        // Find the first: Authenticate
        final Optional<Method> authenticateMethod
                = Arrays.stream(this.methods).filter(
                item -> item.isAnnotationPresent(Authenticate.class))
                .findFirst();
        reference.getAuthorizer().setAuthenticate(authenticateMethod.orElse(null));
        // Find the second: Authorize
        final Optional<Method> authorizeMethod
                = Arrays.stream(this.methods).filter(
                item -> item.isAnnotationPresent(Authorize.class))
                .findFirst();
        reference.getAuthorizer().setAuthorize(authorizeMethod.orElse(null));
    }

    private boolean verifyMethod(final Method[] methods,
                                 final Class<? extends Annotation> clazz) {

        final long found = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(clazz))
                .count();
        // If found = 0, 1, OK
        // If > 1, duplicated
        return 1 < found;
    }
}
