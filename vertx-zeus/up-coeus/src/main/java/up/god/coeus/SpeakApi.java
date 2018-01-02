package up.god.coeus;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@EndPoint
@Path("/coeus")
public class SpeakApi {
    @Path("/speak")
    @POST
    public JsonObject speak(@BodyParam final JsonObject data) {
        System.out.println("Hello: " + data.encode());
        return data;
    }

    @Path("/talk/{word}")
    @GET
    public JsonObject talk(@PathParam("word") final String word) {
        System.out.println(word);
        return new JsonObject().put("result", word);
    }
}
