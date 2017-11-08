package io.vertx.zero.core.equip;

import com.vie.util.Instance;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Transformer;
import org.junit.Assert;
import org.junit.Test;
import top.UnitBase;

public class HttpServerStradaTc extends UnitBase {
    final Transformer<HttpServerOptions> transformer
            = Instance.singleton(HttpServerStrada.class);

    @Test
    public void test_transform_returns_default() {
        final JsonObject source = new JsonObject();
        final HttpServerOptions target = this.transformer.transform(source);
        Assert.assertEquals(HttpServerOptions.DEFAULT_PORT, target.getPort());
        Assert.assertEquals(HttpServerOptions.DEFAULT_HOST, target.getHost());
        Assert.assertEquals(HttpServerOptions.DEFAULT_MAX_WEBSOCKET_MESSAGE_SIZE, target.getMaxWebsocketMessageSize());
        Assert.assertEquals(HttpServerOptions.DEFAULT_ALPN_VERSIONS, target.getAlpnVersions());
        Assert.assertEquals(HttpServerOptions.DEFAULT_COMPRESSION_LEVEL, target.getCompressionLevel());
        // 默认Http2Settings
        final Http2Settings defaultSettings = new Http2Settings();
        // 注意: Http2Settings的maxConcurrentStreams和HttpServerOptions的默认自不一样
        defaultSettings.setMaxConcurrentStreams(HttpServerOptions.DEFAULT_INITIAL_SETTINGS_MAX_CONCURRENT_STREAMS);
        Assert.assertEquals(defaultSettings, target.getInitialSettings());
    }

    @Test
    public void test_transform_returns_custom() {
        final JsonObject source = new JsonObject();
        final JsonObject config = new JsonObject().put("port", 8081);
        source.put("config", config);

        // Port
        HttpServerOptions target = this.transformer.transform(source);
        Assert.assertEquals(8081, target.getPort());

        // Host
        final String EXPECTED_HOST = "127.0.0.1";
        config.put("host", EXPECTED_HOST);
        target = this.transformer.transform(source);
        Assert.assertEquals(EXPECTED_HOST, target.getHost());

        // Compression Level
        final int EXPECTED_C_LEVEL = 7;
        config.put("compressionLevel", EXPECTED_C_LEVEL);
        target = this.transformer.transform(source);
        Assert.assertEquals(EXPECTED_C_LEVEL, target.getCompressionLevel());
    }
}
