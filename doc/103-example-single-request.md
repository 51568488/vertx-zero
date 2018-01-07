# Example: Single Request

This document describe the simplest Rpc workflow in zero system. please refer following pictures:

## ![](/doc/image/exp1-rpc.png)1. Originator

In your originator, you can write code as following:

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

## 2. Terminator

You must be sure current terminator name is "ipc-coeus" in `vertx-server.yml` as following:

```yaml
- name: ipc-coeus
  type: ipc
  config:
    port: 6884
    host: 0.0.0.0
```

Then you can write the code as following:

```java
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;

public class SpeakWorker {

    @Ipc(value = "IPC://EVENT/ADDR")
    public String send(final Envelop envelop) {
        return envelop.toString();
    }
}
```

**\*: Be sure the "to" attribute in Originator is the same as "value" in Terminator**

## 3. Start Up





