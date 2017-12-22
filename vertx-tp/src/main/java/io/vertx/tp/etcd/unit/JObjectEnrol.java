package io.vertx.tp.etcd.unit;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.etcd.Enrol;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.log.Annal;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.requests.EtcdKeyPutRequest;
import mousio.etcd4j.responses.EtcdKeysResponse;

public class JObjectEnrol implements Enrol<JsonObject> {

    private static final Annal LOGGER = Annal.get(JObjectEnrol.class);

    private transient final EtcdClient etcd = EtcdData.create(getClass()).getClient();

    @Override
    public boolean write(final String path,
                         final JsonObject entity) {
        final String data = new String(entity.toBuffer().getBytes());
        final EtcdKeyPutRequest request = this.etcd.put(path, data);
        request.ttl(12);
        try {
            final EtcdResponsePromise<EtcdKeysResponse> response = request.send();
            System.out.println(response.get().getNode());
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public JsonObject read(final String path) {
        return null;
    }
}
