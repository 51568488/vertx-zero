package com.vie.util.io;

import com.vie.exception.ke.EmptyStreamException;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.StoreBase;

public class IOTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp("in.properties"));
    }
}
