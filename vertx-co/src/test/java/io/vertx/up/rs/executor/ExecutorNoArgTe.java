package io.vertx.up.rs.executor;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Instance;
import top.WebBase;

public class ExecutorNoArgTe extends WebBase {

    public void testRequest() throws Exception {
        final Event event = extract(HelloParam.class, "sayHelly");
        this.router.route().handler(context -> {
            final Executor executor = Instance.singleton(DirectHandler.class);
            executor.execute(context, event);
            context.response().end();
        });
        testRequest(HttpMethod.GET, "", 200, "OK");
    }
}
