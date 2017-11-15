package io.vertx.up.web;

import io.vertx.ext.unit.TestContext;
import io.vertx.up.Launcher;
import io.vertx.up.exception.VertxCallbackException;
import io.vertx.zero.test.UnitBase;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

public class ZeroLauncherTc extends UnitBase {

    @Test(expected = VertxCallbackException.class)
    public void testLauncher(final TestContext context) {
        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(null);
    }
}
