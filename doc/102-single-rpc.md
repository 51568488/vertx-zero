# Example One: Single Rpc

In zero system, the developer could focus on code logical instead of Rpc internally, this example will describe Rpc development for micro service communication. For environment preparing, please refer following document

* [2.3 - Micro Service Environment](/doc/23-micro-service-environment.md)
* [10.1 - Rpc Configuration](/doc/101-rpc-configuration.md)

Then you can write your code in zero service.

## 1. Rpc Mode

In zero system, it only support three rpc node:

* Sender
* Transfer
* Final

Here are some different code.

### 1.1. Sender

```java
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.Ipc;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/cronus")
public class SpeakApi {

    @Path("/forward")
    @POST
    @Ipc(to = "IPC://EVENT/ADDR", name = "ipc-coeus")
    public JsonObject ipc(@BodyParam final JsonObject data) {
        System.out.println(data);
        return data;
    }
}
```

### 1.2. Transfer

```java
package up.god.crius;

import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {
    /**
     * Final service
     *
     * @param envelop
     */
    @Ipc(value = "IPC://EVENT/ADDR",
            name = "ipc-coeus", to = "IPC://EVENT/FINAL")
    public String send(final Envelop envelop) {
        System.out.println("Called");
        return "Hello World";
    }
}
```

### 1.3. Final



