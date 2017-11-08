package com.vie.util;

import com.vie.exception.ke.EmptyStreamException;
import com.vie.exception.ke.JsonFormatException;
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
