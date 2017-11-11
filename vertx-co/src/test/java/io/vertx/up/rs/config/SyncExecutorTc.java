package io.vertx.up.rs.config;

import io.vertx.core.http.HttpMethod;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import io.vertx.up.rs.executor.GetParam;
import io.vertx.up.rs.executor.SyncExecutor;
import org.junit.Test;
import org.vie.util.Instance;
import top.WebBase;

public class SyncExecutorTc extends WebBase {

    @Test
    public void testQuery() throws Exception {
        final Event event = extract(GetParam.class, "getQuery");
        this.router.route().handler(context -> {
            final Executor executor = Instance.singleton(SyncExecutor.class);
            executor.execute(context, event);
        });
        testRequest(HttpMethod.GET, "/query?name=Lang", 200, "OK");
    }

    @Test
    public void testPath() throws Exception {
        final Event event = extract(GetParam.class, "getPath");
        this.router.route("/query/:name").handler(context -> {
            final Executor executor = Instance.singleton(SyncExecutor.class);
            executor.execute(context, event);
        });
        testRequest(HttpMethod.GET, "/query/email", 200, "OK");
    }

    @Test
    public void testHeader() throws Exception {
        final Event event = extract(GetParam.class, "getHeader");
        this.router.route("/query/:name").handler(context -> {
            final Executor executor = Instance.singleton(SyncExecutor.class);
            executor.execute(context, event);
        });
        testRequestWithContentType(HttpMethod.GET, "/query/email", "application/json",
                200, "OK");
    }
}
