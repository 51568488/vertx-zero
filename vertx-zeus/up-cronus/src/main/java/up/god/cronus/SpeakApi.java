package up.god.cronus;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.plugin.rpc.RpcClient;

import javax.inject.infix.Rpc;
import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/cronus")
public class SpeakApi {

    @Rpc
    private transient RpcClient client;

    @Path("/forward")
    @POST
    // @Ipc(to = "IPC://EVENT/ADDR", name = "ipc-coeus")
    public JsonObject ipc(@BodyParam final JsonObject data) {
        // Build Rpc Data
        this.client.connect(
                "ipc-coeus",
                "IPC://EVENT/ADDR",
                data, handler -> {

                });
        return data;
    }
}
