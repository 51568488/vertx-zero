package io.vertx.zero.core.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.zero.web.ZeroGrid;
import org.junit.Test;
import top.test.tool.core.VertxTestBase;

public class MongoDbTc extends VertxTestBase {

    @Test
    public void testDb() {
        final JsonObject config = ZeroGrid.getOptions("mongo");
        final MongoClient client = MongoClient.createNonShared(this.vertx,
                config);
        final JsonObject user = new JsonObject().put("title", "Hobbit");
        final Handler<AsyncResult<String>> handler =
                (res) -> {
                    client.save("test", user, callback -> {
                        Future.succeededFuture(callback.result());
                    });
                };

    }
}
