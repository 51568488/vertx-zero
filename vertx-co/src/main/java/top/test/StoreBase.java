package top.test;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.vie.util.Store;

public abstract class StoreBase extends UnitBase {

    protected void execJObject(final String filename,
                               final Handler<AsyncResult<JsonObject>> handler) {
        final ConfigStoreOptions options = Store.getJson(getFile(filename));
        exec(options, handler);
    }

    protected void execYaml(final String filename,
                            final Handler<AsyncResult<JsonObject>> handler) {
        final ConfigStoreOptions options = Store.getYaml(getFile(filename));
        exec(options, handler);
    }

    protected void execProp(final String filename,
                            final Handler<AsyncResult<JsonObject>> handler) {
        final ConfigStoreOptions options = Store.getProp(getFile(filename));
        exec(options, handler);
    }

    private void exec(final ConfigStoreOptions options,
                      final Handler<AsyncResult<JsonObject>> handler) {
        final ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions().addStore(options);
        final Vertx vertx = this.rule.vertx();
        final ConfigRetriever retriever = ConfigRetriever.create(vertx, retrieverOptions);
        retriever.getConfig(handler);
    }
}
