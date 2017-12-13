package up.god.apollo.exp2;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/exp2")
public interface SimpleApi {

    @Path("{name}")
    @GET
    @Address("EXP2://QUEUE/SAY-HELLO")
    public String sayHello(
            @NotNull @PathParam("name") final String lang,
            @NotNull(message = "limit参数不可为空") @QueryParam("limit") final Integer limit);
}
