package io.vertx.up.example.api.jsr303;

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
            @DefaultValue("lang.lang")
            @NotNull(message = "用户名不能为空")
                    String username,
            @QueryParam("password")
            @NotNull(message = "密码不能为空")
                    String password
    );

    @Path("/authorize")
    @POST
    Demo authorize(
            @BodyParam @Valid final Demo demo);
}
