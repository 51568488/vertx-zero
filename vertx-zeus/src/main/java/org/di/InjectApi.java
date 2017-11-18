package org.di;

import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.zero.tool.Jackson;
import org.tlk.api.User;

import javax.inject.infix.Mongo;
import javax.inject.infix.MySql;
import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
public class InjectApi {

    @MySql
    private transient SQLClient client;

    @Mongo
    private transient MongoClient mongo;

    @POST
    @Path("/async/inject")
    @Address("ZERO://INJECT")
    public String sendAsync(
            @BodyParam final User user) {
        final String response = Jackson.serialize(user);
        System.out.println(this.client);
        return response;
    }
}
