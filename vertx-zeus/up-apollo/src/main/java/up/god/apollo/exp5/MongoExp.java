package up.god.apollo.exp5;

import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.EndPoint;

import javax.inject.infix.Mongo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/exp5/")
public class MongoExp {

    @Mongo
    private transient MongoClient client;

    @GET
    @Path("mongo")
    public String sayHello() {
        return "MongoDb";
    }
}
