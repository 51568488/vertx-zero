package javax.ws.rs;

import io.vertx.up.media.resolver.JsonResolver;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BodyParam {
    /**
     * Default resolver to process the body
     */
    Class<?> resolver() default JsonResolver.class;
}
