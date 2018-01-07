# Rpc Configuration

This document is for rpc configuration in zero system. You can configure current zero instance as two Rpc roles: **Server/Client**, Rpc require micro service environment instead of standalone, it means that you must read this document first: [2.3 - Micro Service Environment](/doc/23-micro-service-environment.md) .

## 1. Rpc Server

You can configure rpc server in `vertx-server.yml` directly, it's not needed to create new configuration files.

```yaml
- name: ipc-cronus
  type: ipc
  config:
    port: 6883
    host: 0.0.0.0
    ssl: false
    type: PEM
```

You can add new node of `type=ipc` IPC \( Internal Process Calling \) instead of `http`, Here are some additional configuration info of rpc to enable SSL.

* **ssl**: Whether enable SSL communication with Rpc
* **type**: Here are three types of SSL, please refer gRpc/vertx document to see more details of the types:
  * JKS
  * PKCS12
  * PEM

## 2. Rpc Client

Rpc client configuration must be configured standalone. Here are the comments:

In your `vertx.yml` configuration file, you must extend another `lime` node as following:

```yaml
zero:
  lime: etcd3, rpc
```

Then you must create new file `vertx-rpc.yml` with following content:

```yaml
rpc:
  ssl: false
  uniform:
    type: PEM
  extension:
    type: XXX
```



