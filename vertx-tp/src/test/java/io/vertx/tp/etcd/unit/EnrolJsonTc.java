package io.vertx.tp.etcd.unit;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.TestBase;
import io.vertx.tp.etcd.Enrol;
import io.vertx.up.tool.mirror.Instance;

public class EnrolJsonTc extends TestBase {

    public void testEnrol() {
        final Enrol<JsonObject> enrol = Instance.singleton(JObjectEnrol.class);
        enrol.write("/zero/test", getJson("Store.json"));
    }
}
