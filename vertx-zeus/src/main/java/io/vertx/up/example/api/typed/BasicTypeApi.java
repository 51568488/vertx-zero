package io.vertx.up.example.api.typed;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@EndPoint
@Path("/zero/type")
public class BasicTypeApi {

    @Path("/json")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String testInteger(
            @BodyParam
            @Codex final JsonObject data,
            final HttpServerRequest request,
            @QueryParam("username") final String name,
            @MatrixParam("test") final String test,
            @MatrixParam("a") final String a,
            @MatrixParam("b") final String b) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(test);
        System.out.println(data);
        System.out.println(request);
        System.out.println(name);
        return "Number: Body ";
    }
}
