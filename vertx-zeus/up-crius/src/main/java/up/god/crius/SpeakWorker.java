package up.god.crius;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {
    /**
     * Final service
     *
     * @param envelop
     */
    @Ipc(value = "IPC://EVENT/ADDR")
    public void send(final Message<Envelop> envelop) {
        System.out.println(envelop.body());
    }
}
