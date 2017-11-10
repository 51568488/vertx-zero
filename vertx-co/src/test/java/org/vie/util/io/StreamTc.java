package org.vie.util.io;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.StoreBase;

public class StreamTc extends StoreBase {

    @Test
    public void testRead(final TestContext context) {
        context.assertNotNull(Stream.read(getFile("in.txt")));
    }
}
