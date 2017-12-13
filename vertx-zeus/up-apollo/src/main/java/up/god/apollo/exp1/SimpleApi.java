package up.god.apollo.exp1;

import io.vertx.quiz.Params;
import io.vertx.up.annotations.EndPoint;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/exp1/")
public class SimpleApi {

    @Path("{name}")
    @GET
    public String sayHello(
            @NotNull @PathParam("name") final String lang,
            @NotNull(message = "limit参数不可为空") @QueryParam("limit") final Integer limit) {
        Params.start(getClass()).monitor(lang).monitor(limit).end();
        return lang;
    }
}
