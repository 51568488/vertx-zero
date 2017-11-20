package io.vertx.up.example.api.jsr303;

import io.vertx.up.annotations.EndPoint;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/zero/user")
@EndPoint
public interface UserApi {

    @Path("/login")
    @GET
    String login(
            @QueryParam("username") @NotNull(message = "用户名不能为空")
                    String username,
            @QueryParam("password") @NotNull(message = "密码不能为空")
                    String password
    );
}
