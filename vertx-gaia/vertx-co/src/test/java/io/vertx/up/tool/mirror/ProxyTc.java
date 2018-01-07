package io.vertx.up.tool.mirror;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import org.junit.Test;

public class ProxyTc extends ZeroBase {
    @Test
    public void testInvoke(final TestContext context) {
        final Object proxy = Fantam.getProxy(Hello.class);
        Instance.invoke(proxy, "sayHello");
    }
}

interface Hello {
    void sayHello();
}