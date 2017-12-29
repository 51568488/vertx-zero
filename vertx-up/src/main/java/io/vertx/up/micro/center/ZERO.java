package io.vertx.up.micro.center;

interface Info {

    String ETCD_STATUS = "[ ZERO ] ( Etcd Center ) The status {0} of service {1} " +
            "has been registried to {2}.";

    String ETCD_READ = "[ ZERO ] ( Etcd Catalog ) Read the path {0} service lists.";

    String ETCD_ROUTE = "[ ZERO ] ( Etcd Catalog ) The following routes has been push to \"{0}\": " +
            "\n\t[ Up Micro ] Micro Service = {1}," +
            "\n\t[ Up Micro ] EndPoint = {2}" +
            "\n\t[ Up Micro ] Route Uris = {3}" +
            "\n\t[ Up Micro ] Successfully to registeried, wait for discovery.";
}
