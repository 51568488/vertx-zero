# Enable Rpc Client

Rpc Client is used for communicating between different micro service in zero framework. Zero system support two modes

* Stream Mode \( Static \) - Refer [10.3 - Example: Single Request](/doc/103-example-single-request.md) for Examples
* Dynamic Mode - This document will describe dynamic mode about how to use RpcClient in zero system

## 1. Configuration

For Rpc default configuration, it's not needed to describe here, but we need add standard infix configuration.

**vertx.yml**

```yaml
zero:
  lime: mongo, etcd3, rpc
  vertx:
    instance:
    - name: vx-zero
      options:
        # Fix block 2000 limit issue.
        maxEventLoopExecuteTime: 30000000000
```

**vertx-rpc.yml**

```yaml
rpc:
  ssl: false
  uniform:
    type: PEM
```

**vertx-inject.yml**

```yaml
mongo: io.vertx.up.plugin.mongo.MongoInfix
rpc: io.vertx.up.plugin.rpc.RpcInfix
```



