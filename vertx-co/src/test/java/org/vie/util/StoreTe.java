package org.vie.util;

import io.vertx.exception.zero.EmptyStreamException;
import io.vertx.exception.zero.JsonFormatException;
import org.junit.Test;
import top.StoreBase;

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
