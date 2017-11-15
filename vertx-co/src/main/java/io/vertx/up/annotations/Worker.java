package io.vertx.up.annotations;

import io.vertx.up.eon.ZeroValue;
import io.vertx.up.eon.em.MessageModel;

import java.lang.annotation.*;

/**
 * Worker will consume event bus data by address
 * Agent -> Endpoint -> ( Jsr311 ) -> Address Method ( To ) -> EventBus
 * EventBus -> Worker -> Method -> Address Method ( From )
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Worker {
    /**
     * Event but interaction mode definition
     *
     * @return
     */
    MessageModel value() default MessageModel.REQUEST_RESPONSE;

    /**
     * Worker Instance Number
     * Default: 32
     *
     * @return
     */
    int instances() default ZeroValue.DEFAULT_INSTANCES;

    /**
     * Isolation Group
     * Default: __VERTX_ZERO__
     *
     * @return
     */
    String group() default ZeroValue.DEFAULT_GROUP;

    /**
     * @return
     */
    boolean ha() default ZeroValue.DEFAULT_HA;
}
