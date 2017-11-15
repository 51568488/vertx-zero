package io.vertx.zero.tool.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.test.StoreBase;
import org.junit.Test;

public class IOTc extends StoreBase {
    @Test
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp(getFile("in.properties")));
    }

    @Test
    public void testYaml(final TestContext context) {
        context.assertNotNull(IO.getYaml(getFile("test.yaml")));
    }
}
