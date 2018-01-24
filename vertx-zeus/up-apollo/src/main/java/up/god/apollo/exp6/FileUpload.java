package up.god.apollo.exp6;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import java.io.File;

@EndPoint
@Path("/exp6/")
public class FileUpload {

    @POST
    @Path("/upload")
    public JsonObject upload(@Codex @StreamParam final File file) {
        System.out.println(file.getAbsoluteFile());
        return null;
    }

    @POST
    @Path("/json")
    public JsonObject test(@Codex @BodyParam final JsonObject data) {

        return data;
    }

    @GET
    @Path("/valid")
    public String test(
            @QueryParam("username")
            @NotEmpty(message = "{account.username}") final String username) {
        return username;
    }
}
