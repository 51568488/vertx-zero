package io.vertx.tp.etcd.unit;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.TestBase;
import io.vertx.tp.etcd.Enrol;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.tool.mirror.Instance;
import org.junit.Test;

public class EnrolJsonTc extends TestBase {

    public void testWrite() {
        final Enrol<JsonObject> enrol = Instance.singleton(JObjectEnrol.class);
        enrol.write("/zero/ipc/192.168.0.100/8080/", getJson("Store.json"));
    }

    public void testRead() {
        final Enrol<JsonObject> enrol = Instance.singleton(JObjectEnrol.class);
        final JsonObject data = enrol.read("/zero/ipc/192.168.0.100/8080/");
        System.out.println(data);
    }

    @Test
    public void testDelete() {
        final EtcdData etcdData = EtcdData.create(getClass());
        etcdData.delete("/zero/ipc/192.168.0.100/8080/");
    }
}
