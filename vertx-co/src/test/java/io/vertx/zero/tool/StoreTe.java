package io.vertx.zero.tool;

import io.vertx.zero.exception.EmptyStreamException;
import io.vertx.zero.exception.JsonFormatException;
import io.vertx.zero.test.StoreBase;
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
