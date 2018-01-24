package javax.ws.rs;

import io.vertx.up.media.resolver.UnsetResolver;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StreamParam {
    /**
     * Default resolver to process the stream body
     */
    Class<?> resolver() default UnsetResolver.class;
}
