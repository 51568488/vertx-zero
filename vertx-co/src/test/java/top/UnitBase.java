package top;

import com.vie.core.reliable.Insurer;
import com.vie.exception.ZeroException;
import com.vie.util.Instance;
import com.vie.util.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public abstract class UnitBase {
    @Rule
    public final RunTestOnContext rule = new RunTestOnContext();

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

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

    protected String getFile(final String filename) {
        final Class<?> clazz = getClass();
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        Log.info(getLogger(), "[TEST] Test input file: {0}", file);
        return file;
    }
}
