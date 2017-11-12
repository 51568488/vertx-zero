package org.vie.util.io;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import io.vertx.exception.zero.EmptyStreamException;
import top.StoreBase;

public class StreamTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("ini.txt")));
    }
}
