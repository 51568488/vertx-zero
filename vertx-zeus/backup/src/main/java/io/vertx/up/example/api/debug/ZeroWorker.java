package io.vertx.up.example.api.debug;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class ZeroWorker {

    @Address("ZERO://EVENT")
    public Envelop test(final Envelop envelop) {
        System.out.println("Hello Envelop");
        System.out.println("Data: " + envelop.data());
        return envelop;
    }
}
