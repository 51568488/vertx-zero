package up.god.crius;

import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {
    /**
     * Final service
     *
     * @param envelop
     */
    @Ipc(value = "IPC://EVENT/ADDR")
    public String send(final Envelop envelop) {
        System.out.println("Called");
        return "Hello World";
    }
}
