package io.vertx.up.annotations;

import java.lang.annotation.*;

/**
 * Marked as route, every vertx route must contains
 *
 * @Routine annotation to avoid scan method directly
 * 1. Each route must be marked as Routine;
 * 2. Each routine class should be pojo as JSR311;
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Routine {
}
