package io.vertx.up.annotations;

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
}
