package up.god.cronus;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.EndPoint;

import javax.inject.infix.Mongo;
import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/cronus")
public class SpeakApi {

    @Mongo
    private transient MongoClient client;

    @Path("/speak")
    @POST
    public JsonObject speak(@BodyParam final JsonObject data) {
        System.out.println("Hello: " + data.encode());
        return data;
    }
}
