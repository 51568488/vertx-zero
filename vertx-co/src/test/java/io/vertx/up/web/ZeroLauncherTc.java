package io.vertx.up.web;

import io.vertx.ext.unit.TestContext;
import io.vertx.up.Launcher;
import org.junit.Test;
import org.vie.exception.up.VertxCallbackException;
import org.vie.util.Instance;
import top.UnitBase;

public class ZeroLauncherTc extends UnitBase {

    @Test(expected = VertxCallbackException.class)
    public void testLauncher(final TestContext context) {
        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(null);
    }
}
