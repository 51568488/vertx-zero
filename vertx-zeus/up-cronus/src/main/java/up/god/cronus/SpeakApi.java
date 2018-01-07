package up.god.cronus;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.Ipc;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/cronus")
public class SpeakApi {

    @Path("/forward")
    @POST
    @Ipc(to = "IPC://EVENT/ADDR", name = "ipc-coeus")
    public JsonObject ipc(@BodyParam final JsonObject data) {
        System.out.println(data);
        return data;
    }
}
