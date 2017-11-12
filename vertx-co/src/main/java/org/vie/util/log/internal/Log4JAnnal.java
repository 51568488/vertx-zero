package org.vie.util.log.internal;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.exception.ZeroException;
import org.vie.util.Log;
import org.vie.util.log.Annal;

public class Log4JAnnal implements Annal {

    private transient final Logger logger;

    public Log4JAnnal(final Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void zero(final ZeroException ex) {
        Log.zero(this.logger, ex);
    }

    @Override
    public void warn(final String key, final Object... args) {
        Log.warn(this.logger, key, args);
    }

    @Override
    public void vertx(final VertxException ex) {
        Log.vertx(this.logger, ex);
    }

    @Override
    public void jvm(final Throwable ex) {
        Log.jvm(this.logger, ex);
    }

    @Override
    public void info(final String key, final Object... args) {
        Log.info(this.logger, key, args);
    }

    @Override
    public void debug(final String key, final Object... args) {
        Log.debug(this.logger, key, args);
    }
}
