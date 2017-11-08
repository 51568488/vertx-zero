package com.vie.util.io;

import com.vie.exception.ke.EmptyStreamException;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.StoreBase;

public class StreamTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("ini.txt")));
    }
}
