package io.vertx.zero.marshal.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.test.core.VertxTestBase;
import org.junit.Test;

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
