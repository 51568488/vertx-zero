package io.vertx.zero.tool.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.test.StoreBase;
import org.junit.Test;

public class StreamTc extends StoreBase {

    @Test
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("in.txt")));
    }
}
