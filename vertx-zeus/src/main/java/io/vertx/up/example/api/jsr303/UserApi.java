package io.vertx.up.example.api.jsr303;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.example.domain.Demo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@Path("/zero/user")
@EndPoint
public interface UserApi {

    @Path("/login")
    @GET
    String login(
            @QueryParam("username")
            @NotNull(message = Validation.UserName.NOT_NULL)
                    String username,
            @QueryParam("password")
            @NotNull(message = Validation.Password.NOT_NULL)
                    String password
    );

    @Path("/authorize")
    @POST
    Demo authorize(
            @BodyParam @Valid final Demo demo);

    @Path("/json")
    @POST
    JsonObject serialize(
            @BodyParam @Valid final JsonObject data);
}
