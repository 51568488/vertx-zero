package org.vie.util.io;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.test.StoreBase;

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
