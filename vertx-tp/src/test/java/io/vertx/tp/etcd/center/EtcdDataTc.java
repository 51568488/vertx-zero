package io.vertx.tp.etcd.center;

import io.vertx.quiz.TestBase;
import org.junit.Test;

public class EtcdDataTc extends TestBase {

    @Test
    public void testData() {
        EtcdData.create(getClass());
    }
}
