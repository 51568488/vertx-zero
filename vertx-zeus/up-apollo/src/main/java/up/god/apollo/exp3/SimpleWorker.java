package up.god.apollo.exp3;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.Params;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.kidd.Rapider;

@Queue
public class SimpleWorker {

    @Address("EXP3://QUEUE/VALIDATE")
    public void sayHello(final Message<Envelop> message) {
        final JsonObject data = Rapider.getJson(message);
        Params.start(getClass()).monitor(data).end();
        message.reply(Envelop.success("Response Successfully"));
    }
}
