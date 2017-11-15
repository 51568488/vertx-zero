package io.vertx.zero.log;

import io.vertx.zero.test.UnitBase;
import org.junit.Test;

public class AnnalTk extends UnitBase {

    @Test
    public void testAnnal() {
        final Annal logger = Annal.get(AnnalTk.class);
    }
}
