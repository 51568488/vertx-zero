package io.vertx.tp.plugin.qiy;

import io.vertx.quiz.ext.web.WebTestBase;
import org.junit.Assert;
import org.junit.Test;

public class QiyClientTc extends WebTestBase {

    @Test
    public void testRequestToken() {
        final QiyClient client = QiyClient.createShared(this.vertx);
        client.authorize(handler -> {
            Assert.assertNotNull(handler.result());
            this.getLogger().info("[ TEST ] Data: {0}", handler.result());
        });
    }

    @Test
    public void testRefreshToken() {
        final QiyClient client = QiyClient.createShared(this.vertx);
        client.authorize(handler -> {
            Assert.assertNotNull(handler.result());
            final String refreshToken = handler.result().getString("refresh_token");
            client.refreshToken(refreshToken, res -> {
                Assert.assertNotNull(res.result());
                this.getLogger().info("[ TEST ] Data: {0}", res.result());
            });
        });
    }

    @Test
    public void testRequestUpload() {
        final QiyClient client = QiyClient.createShared(this.vertx);
        client.authorize(handler -> {
            Assert.assertNotNull(handler.result());
            client.requestFile("mp4", "200", res -> {
                Assert.assertNotNull(res.result());
                this.getLogger().info("[ TEST ] Data: {0}", res.result());
                Assert.assertNotNull(res.result().getValue("file_id"));
                Assert.assertNotNull(res.result().getValue("upload_url"));
            });
        });
    }
}
