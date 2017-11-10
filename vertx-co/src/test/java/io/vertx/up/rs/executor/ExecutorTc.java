package io.vertx.up.rs.executor;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Instance;
import top.WebBase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

public class ExecutorTc extends WebBase {

    public void testRequest() throws Exception {
        this.router.route("/query?a=1,b=2").handler(context -> {
            final Event event = extract(TestParam.class, "test");
            final Executor executor = Instance.singleton(DirectHandler.class);
            executor.execute(context, event);
        });
        testRequest(HttpMethod.GET, "/query", 200, "OK");
    }
}

@EndPoint
class TestParam {

    @Path("/query")
    @GET
    public void test(final @QueryParam("a") String a) {
        System.out.println(a);
    }
}
