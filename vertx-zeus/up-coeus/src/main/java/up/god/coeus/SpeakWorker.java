package up.god.coeus;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {
    /**
     * Middle service
     *
     * @param envelop
     */
    @Ipc(value = "IPC://EVENT/ADDR",
            name = "ipc-atlas", to = "IPC://EVENT/ADDR")
    public void send(final Message<Envelop> envelop) {
        envelop.reply(Envelop.success("Hello Lang"));
    }
}
