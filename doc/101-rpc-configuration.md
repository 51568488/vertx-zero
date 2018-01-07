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

You can add new node of `type=ipc` IPC \( Internal Process Calling \).

