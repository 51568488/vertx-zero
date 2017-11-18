package org.tlk.api;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.zero.tool.Jackson;
import org.tlk.exception.TestRequestException;

import javax.inject.infix.Mongo;

public class UserWorker {

    @Mongo
    private transient MongoClient client;

    @Address("ZERO://ROLE")
    public void async(final Message<Envelop> message) {
        final User user = Envelop.data(message, User.class);
        final JsonObject userData = new JsonObject(Jackson.serialize(user));
        this.client.save("DB_USER", userData, res -> {
            if (res.succeeded()) {
                message.reply(Envelop.success("Hello World"));
            } else {
                res.cause().printStackTrace();
            }
        });
    }

    @Address("ZERO://USER")
    public Envelop reply(final Envelop message) {
        final User user = message.data(User.class);
        final WebException error = new TestRequestException(getClass(),
                "Lang", "Detail");
        return Envelop.failure(error);
    }


}
