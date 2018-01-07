package io.vertx.up.annotations;

import java.lang.annotation.*;

/**
 * Only injections that marked with @Plugin could be deployed by
 * VertxPlugin component.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Plugin {
}
