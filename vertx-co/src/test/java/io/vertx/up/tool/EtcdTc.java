package io.vertx.up.tool;

import mousio.etcd4j.EtcdClient;

import java.net.URI;

public class EtcdTc {
    public void testEtcd() throws Exception {
        try (EtcdClient client = new EtcdClient(
                URI.create("http://localhost:6189")
        )) {
            System.out.println(client.getVersion());
        }
    }
}
