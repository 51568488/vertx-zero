package io.vertx.zero.log;

import io.vertx.quiz.ZeroBase;
import io.vertx.up.log.Annal;
import org.junit.Test;

public class AnnalTk extends ZeroBase {

    @Test
    public void testAnnal() {
        final Annal logger = Annal.get(AnnalTk.class);
    }
}
