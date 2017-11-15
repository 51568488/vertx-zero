package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

public class ZeroErrorTc extends ZeroBase {

    @Test
    public void testError(final TestContext context) {
        final JObjectBase node
                = Instance.singleton(ZeroError.class);
        final JsonObject map = node.read();
    }
}
