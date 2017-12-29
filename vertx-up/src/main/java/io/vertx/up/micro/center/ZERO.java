package io.vertx.up.micro.center;

interface Info {

    String ETCD_STATUS = "[ ZERO ] ( Etcd Center ) The status {0} of service {1} " +
            "has been registried to {2}.";

    String ETCD_READ = "[ ZERO ] ( Etcd Catalog ) Read the path {0} service lists.";

    String ETCD_IGNORE = "[ ZERO ] ( Etcd Catalog ) The path {0} has been skipped.";
}
