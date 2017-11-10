package io.vertx.up.annotations;

import java.lang.annotation.*;

/**
 * Event bus annotation
 * If it's annotated on method, it means that this action enabled event bus.
 * Otherwise use sync response directly.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Address {
    /**
     * Target address
     *
     * @return
     */
    String to();

    /**
     * From address
     *
     * @return
     */
    String from();
}
