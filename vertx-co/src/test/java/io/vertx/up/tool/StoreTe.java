package io.vertx.up.tool;

import io.vertx.quiz.StoreBase;
import io.vertx.zero.exception.heart.EmptyStreamException;
import io.vertx.zero.exception.heart.JsonFormatException;
import org.junit.Test;

public class StoreTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testNone() {
        execJObject("zero.json", config -> {

        });
    }

    @Test(expected = JsonFormatException.class)
    public void testEmpty() {
        execJObject("format.json", config -> {

        });
    }
}
