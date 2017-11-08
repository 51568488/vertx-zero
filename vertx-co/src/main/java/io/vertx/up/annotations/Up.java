package io.vertx.up.annotations;

import com.vie.cv.Strings;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Up {
    /**
     * If set, start scan from setted package,
     * otherwise, scan the whole project.
     *
     * @return
     */
    String zeroScan() default Strings.EMPTY;
}
