package io.vertx.up.web.center;

interface Info {

    String ETCD_STATUS = "[ ZERO ] ( Etcd Center ) The status {0} of service {1} has been registried to {2}.";

    String ETCD_DATA = "[ ZERO ] ( Etcd Center ) The data {0} of service {1} has been registried to {2}.";

    String ETCD_UN_DATA = "[ ZERO ] ( Etcd Center ) The data node {0} has been deleted because of service {1} down.";
}
