package up.god.coeus;

import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {
    /**
     * Middle service
     *
     * @param envelop
     */
    @Ipc(value = "IPC://EVENT/ADDR")
    public String send(final Envelop envelop) {
        System.out.println("Called");
        return "Hello World";
    }
}
