package org.vie.util;

import org.junit.Test;
import org.vie.exception.run.EmptyStreamException;
import org.vie.exception.run.JsonFormatException;
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
