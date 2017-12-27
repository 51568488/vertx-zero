package io.vertx.up.tool.net;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.tool.Net;
import org.junit.Test;

public class NetTc extends ZeroBase {

    @Test
    public void testIp(final TestContext context) {
        final String ip = Net.getIP();
        getLogger().info("[ ZERO TK ] Ip Address = {0}.", ip);
        context.assertNotNull(ip);
    }

    @Test
    public void testIpV4(final TestContext context) {
        final String ip = Net.getIPv4();
        getLogger().info("[ ZERO TK ] Ip v4 Address = {0}.", ip);
        context.assertNotNull(ip);
    }

    @Test
    public void testIpV6(final TestContext context) {
        final String ip = Net.getIPv6();
        getLogger().info("[ ZERO TK ] Ip v6 Address = {0}.", ip);
        context.assertNotNull(ip);
    }
}
