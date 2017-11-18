package org.di;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Qualifier;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.zero.log.Errors;
import org.tlk.api.User;
import org.tlk.exception.TestRequestException;

import javax.inject.Inject;

@Queue
public class InjectWorker {

    @Inject
    private transient InjectDao dao;

    @Inject
    private transient InjectStub stub;

    @Inject
    @Qualifier("NameInjectA")
    private transient InjectA injectA;

    @Inject
    private transient InjectNo injectNo;

    @Address("ZERO://INJECT")
    public Envelop reply(final Envelop message) {
        System.out.println(this.dao);
        System.out.println(this.stub);
        System.out.println(this.injectA);
        final User user = message.data(User.class);
        try {
            Errors.normalize(getClass(), -100001, "Lang", "Detail");
        } catch (final Throwable ex) {
            ex.printStackTrace();
        }
        final WebException error = new TestRequestException(getClass(),
                "Lang", "Detail");
        return Envelop.failure(error);
    }
}
