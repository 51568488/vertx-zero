package io.vertx.tp.plugin.qiy;

import io.vertx.core.json.JsonObject;
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
    public void testRequestTokenInvalid() {
        final QiyClient client = QiyClient.createShared(this.vertx);
        client.authorize(new JsonObject()
                .put("client_id", "test")
                .put("client_secret", "random"), handler -> {
            Assert.assertNotNull(handler.cause());
            this.getLogger().info("[ TEST ] Error: {0}", handler.cause());
        });
    }
}
