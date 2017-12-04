package io.vertx.tp.atom;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.tp.exception.TpKeyMissingException;
import org.junit.Test;

public class FeignDepotTc extends ZeroBase {

    @Test(expected = TpKeyMissingException.class)
    public void testFeign(final TestContext context) {
        FeignDepot.create("tvk");
    }

    @Test
    public void testTlk(final TestContext context) {
        final FeignDepot depot = FeignDepot.create("tlk");
    }
}
