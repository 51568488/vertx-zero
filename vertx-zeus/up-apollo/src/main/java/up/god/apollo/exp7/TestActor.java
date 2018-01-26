package up.god.apollo.exp7;

import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class TestActor {

    @Address("ZERO://EXP7/VERIFY")
    public void verify(final Message<Envelop> message) {

    }

    @Address("ZERO://EXP7/WRONG")
    public void wrong(final Message<Envelop> message) {
        Future.failedFuture(Ux.toError(_90001Exception.class, getClass()))
                .setHandler(Ux.toHandler(message));
    }
}
