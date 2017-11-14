package io.vertx.up.annotations.infix;

import java.lang.annotation.*;

/**
 * Standard annotation for Vert.x module to inject instance variable
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mongo {
}
