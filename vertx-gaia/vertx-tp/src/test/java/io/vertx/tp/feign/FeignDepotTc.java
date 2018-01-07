package io.vertx.tp.feign;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.zero.exception.DynamicKeyMissingException;
import org.junit.Test;

public class FeignDepotTc extends ZeroBase {

    @Test(expected = DynamicKeyMissingException.class)
    public void testFeign(final TestContext context) {
        FeignDepot.create("tvk");
    }

    @Test
    public void testTlk(final TestContext context) {
        final FeignDepot depot = FeignDepot.create("tlk");
    }
}
