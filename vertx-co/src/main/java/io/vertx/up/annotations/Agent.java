package io.vertx.up.annotations;

import java.lang.annotation.*;

/**
 * Vert.x filter for each web request
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Agent {

}
