package org.vie.util.io;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import org.vie.exception.run.EmptyStreamException;
import top.StoreBase;

public class IOTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp("in.properties"));
    }
}
