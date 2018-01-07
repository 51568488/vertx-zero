# Example One: Single Rpc

In zero system, the developer could focus on code logical instead of Rpc internally, this example will describe Rpc development for micro service communication. For environment preparing, please refer following document

* [2.3 - Micro Service Environment](/doc/23-micro-service-environment.md)
* [10.1 - Rpc Configuration](/doc/101-rpc-configuration.md)

Then you can write your code in zero service.

## 1. Rpc Mode

In zero system, it only support three rpc node:

* Originator
* Coordinator
* Terminator

Here are some different code.

### 1.1. Originator

```java
    @Ipc(to = "IPC://EVENT/ADDR", name = "ipc-coeus")
    public JsonObject ipc(@BodyParam final JsonObject data) ...
```

### 1.2. Coordinator

```java
    @Ipc(value = "IPC://EVENT/ADDR",
            name = "ipc-coeus", to = "IPC://EVENT/FINAL")
    public String send(final Envelop envelop) ...
```

### 1.3. Terminator

```java
    @Ipc(value = "IPC://EVENT/FINAL")
    public String send(final Envelop envelop) ...
```

### 1.4. Workflow

![](/doc/image/rpc-workflow.png)As above describe, in one request, there should be following roles:

* Sender + Final
* 


