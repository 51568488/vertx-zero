package up.god.apollo.exp7;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class TestActor {

    @Address("ZERO://EXP7/VERIFY")
    public void verify(final Message<Envelop> message) {

    }
}
