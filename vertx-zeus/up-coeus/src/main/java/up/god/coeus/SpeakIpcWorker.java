package up.god.coeus;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class SpeakIpcWorker {

    @Ipc("IPC://EVENT/ADDR")
    public void send(final Message<Envelop> envelop) {
        envelop.reply(Envelop.success("Hello Lang"));
    }
}
