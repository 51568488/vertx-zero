package org.tlk.api;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.ce.Envelop;

@Queue
public class UserWorker {

    @Address("ZERO://USER")
    public Envelop reply(final Envelop message) {
        final User user = message.data(User.class);
        System.out.println(user);
        return Envelop.success(user);
    }

    @Address("ZERO://ROLE")
    public void async(final Message<Envelop> message) {
        
    }
}
