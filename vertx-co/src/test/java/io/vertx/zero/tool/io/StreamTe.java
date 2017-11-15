package io.vertx.zero.tool.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.up.exception.zero.EmptyStreamException;
import io.vertx.zero.test.StoreBase;
import org.junit.Test;

public class StreamTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("ini.txt")));
    }
}
