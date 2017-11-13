package io.vertx.zero.web;

import io.vertx.exception.up.VertxCallbackException;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.Launcher;
import org.junit.Test;
import org.vie.util.Instance;
import top.test.UnitBase;

public class ZeroLauncherTc extends UnitBase {

    @Test(expected = VertxCallbackException.class)
    public void testLauncher(final TestContext context) {
        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(null);
    }
}
