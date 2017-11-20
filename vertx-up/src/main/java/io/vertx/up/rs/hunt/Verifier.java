package io.vertx.up.rs.hunt;

import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

public class Verifier {

    private static final Validator VALIDATOR
            = Validation.buildDefaultValidatorFactory().getValidator();

    private static Verifier INSTANCE;

    public static Verifier create() {
        if (null == INSTANCE) {
            synchronized (Verifier.class) {
                if (null == INSTANCE) {
                    INSTANCE = new Verifier();
                }
            }
        }
        return INSTANCE;
    }

    public <T> void verifyMethod(
            final T proxy,
            final Method method,
            final Object[] args) {
        // 1. Get method validator
        final ExecutableValidator validatorParam
                = VALIDATOR.forExecutables();
        // 2. Create new params that wait for validation
        final Set<ConstraintViolation<T>> constraints
                = validatorParam.validateParameters(proxy, method, args);
        // 3. System.out.println
        for (final ConstraintViolation<T> item : constraints) {
            final WebException error
                    = new _400ValidationException(getClass(),
                    proxy.getClass(), method, item.getMessage());
            error.setReadible(item.getMessage());
            throw error;
        }
    }
}
