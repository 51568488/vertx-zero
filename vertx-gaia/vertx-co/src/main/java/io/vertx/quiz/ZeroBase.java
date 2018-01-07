package io.vertx.quiz;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.log.Log;
import io.vertx.zero.marshal.reliable.Insurer;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class ZeroBase extends TestBase {
    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    public void ensure(final Class<?> insurerCls,
                       final JsonObject data, final JsonObject rule)
            throws ZeroException {
        final Insurer insurer
                = Instance.singleton(insurerCls);
        Log.info(getLogger(), "[TEST] Input data ( Object ): {0}", data);
        Log.info(getLogger(), "[TEST] Rule data: {0}", rule);
        insurer.flumen(data, rule);
    }

    public void ensure(final Class<?> insurerCls,
                       final JsonArray array, final JsonObject rule)
            throws ZeroException {
        final Insurer insurer
                = Instance.singleton(insurerCls);
        Log.info(getLogger(), "[TEST] Input data ( Array ): {0}", array);
        Log.info(getLogger(), "[TEST] Rule data: {0}", rule);
        insurer.flumen(array, rule);
    }
}
