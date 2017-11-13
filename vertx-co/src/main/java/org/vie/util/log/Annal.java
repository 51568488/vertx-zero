package org.vie.util.log;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.exception.ZeroException;
import io.vertx.zero.web.ZeroAmbient;
import org.vie.util.Instance;
import org.vie.util.Log;
import org.vie.util.log.internal.Log4JAnnal;

/**
 * Unite Logging system connect to vert.x, tool kit of Vertx-Zero
 */
public interface Annal {

    void vertx(VertxException ex);

    void zero(ZeroException ex);

    void jvm(Throwable ex);

    void warn(String key, Object... args);

    void info(String key, Object... args);

    void debug(String key, Object... args);

    static Annal get(final Class<?> clazz) {
        return new CommonAnnal(clazz);
    }
}

class CommonAnnal implements Annal {

    private static final Logger RECORD =
            LoggerFactory.getLogger(CommonAnnal.class);

    private transient final Annal logger;

    public CommonAnnal(final Class<?> clazz) {
        Class<?> inject = ZeroAmbient.getPlugin("logger");
        if (null == inject) {
            Log.debug(RECORD, Info.INF_INJECT, clazz);
            inject = Log4JAnnal.class;
        }
        Log.debug(RECORD, Info.INF_ANNAL, inject, clazz);
        this.logger = Instance.instance(inject, clazz);
    }

    @Override
    public void warn(final String key, final Object... args) {
        this.logger.warn(key, args);
    }

    @Override
    public void vertx(final VertxException ex) {
        this.logger.vertx(ex);
    }

    @Override
    public void zero(final ZeroException ex) {
        this.logger.zero(ex);
    }

    @Override
    public void jvm(final Throwable ex) {
        this.logger.jvm(ex);
    }

    @Override
    public void info(final String key, final Object... args) {
        this.logger.info(key, args);
    }

    @Override
    public void debug(final String key, final Object... args) {
        this.logger.debug(key, args);
    }
}
