package io.vertx.zero.core.config;

import com.vie.util.Instance;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.UnitBase;

public class ZeroErrorTc extends UnitBase {

    @Test
    public void testError(final TestContext context) {
        final JObjectBase node
                = Instance.singleton(ZeroError.class);
        final JsonObject map = node.read();
    }
}
