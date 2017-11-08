package io.vertx.zero;

import com.vie.hors.ZeroException;
import io.vertx.core.impl.VertxFactoryImpl;
import io.vertx.core.spi.VertxFactory;

/**
 * Application start information
 */
public class Application {

    public void start() throws ZeroException {
        final VertxFactory factory = new VertxFactoryImpl();
    }
}
