package up.god.cronus;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class SpeakAddrWorker {

    @Address("ZUES://DIRECT")
    public void direct(final Message<Envelop> data) {
        final JsonObject replied = (data.body().data(0, JsonObject.class));
        data.reply(Envelop.success(replied));
    }
}
