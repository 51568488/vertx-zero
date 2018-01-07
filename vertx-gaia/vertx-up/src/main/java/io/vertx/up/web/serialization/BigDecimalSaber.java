package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * BigDecimal
 */
@SuppressWarnings("unchecked")
public class BigDecimalSaber extends DecimalSaber {
    @Override
    protected boolean isValid(final Class<?> paramType) {
        return BigDecimal.class == paramType;
    }

    @Override
    protected Function<String, BigDecimal> getFun() {
        return BigDecimal::new;
    }

    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            final BigDecimal decimal = (BigDecimal) input;
            return decimal.doubleValue();
        }, input);
    }
}
