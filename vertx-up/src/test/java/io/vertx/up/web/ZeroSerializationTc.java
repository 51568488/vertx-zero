package io.vertx.up.web;

import io.vertx.quiz.ZeroBase;
import org.junit.Test;

public class ZeroSerializationTc extends ZeroBase {

    @SuppressWarnings("unchecked")
    private <T> T get(final Class<?> clazz, final String literal) {
        final T value = (T) ZeroSerializer.getValue(clazz, literal);
        getLogger().info("[TEST] Data = {0}, Type = {1}.", literal, clazz.getName());
        return value;
    }

    @Test
    public void testInt() {
        get(Integer.class, "1234");
        get(int.class, "123456");
    }

    @Test
    public void testString() {
        get(String.class, "1234");
    }
}
