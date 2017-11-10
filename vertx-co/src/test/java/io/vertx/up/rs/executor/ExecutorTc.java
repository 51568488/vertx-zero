package io.vertx.up.rs.executor;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.junit.Test;
import org.vie.util.Instance;
import top.WebBase;

import javax.ws.rs.*;
import java.math.BigDecimal;
import java.util.Date;

public class ExecutorTc extends WebBase {

    @Test
    public void testRequest() throws Exception {
        final Event event = extract(TestParam.class, "test");
        this.router.route().handler(context -> {
            final Executor executor = Instance.singleton(DirectHandler.class);
            executor.execute(context, event);
            context.response().end();
        });
        testRequest(HttpMethod.GET, "", 200, "OK");
    }
}

@EndPoint
class TestParam {

    @Path("/query/{name}")
    @GET
    public void test(
            final @DefaultValue("Str0") @QueryParam("str0") String str1,
            final @DefaultValue("12") @FormParam("int0") Integer int0,
            final @DefaultValue("33") @PathParam("name") int int1,
            final @DefaultValue("false") @HeaderParam("present") Boolean present,
            final @DefaultValue("33.3") @CookieParam("cookie") BigDecimal decimal,
            final @DefaultValue("33.5") @QueryParam("amount") float amount,
            final @DefaultValue("33.3") @CookieParam("double") double wrapper,
            final @DefaultValue("2012-11-11") @QueryParam("date") Date created,
            final @DefaultValue("{\"name\":\"Lang\"}") @QueryParam("test") JsonObject data
    ) {
        System.out.println(int0);
    }
}
