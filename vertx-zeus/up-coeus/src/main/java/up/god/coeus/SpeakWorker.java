package up.god.coeus;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {

    @Ipc(value = "IPC://EVENT/ADDR")
    public Future<Envelop> send(final Envelop envelop) {
        final JsonObject data = envelop.data(JsonObject.class);
        data.put("role", "Terminator");
        return Future.succeededFuture(Envelop.success(data));
    }
}
