package io.vertx.up.annotations;

import io.vertx.up.eon.em.IpcType;

import java.lang.annotation.*;

/**
 * Internal Rpc Channel
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Ipc {
    /**
     * Communication type ( 4 categories )
     *
     * @return
     */
    IpcType type() default IpcType.UNITY;

    /**
     * Default Rpc Service Name
     * 1. name != "" -> Sender
     * 2. name == "" -> Consumer ( Worker )
     *
     * @return
     */
    String name() default "";

    /**
     * Event Bus address
     *
     * @return
     */
    String value();
}
