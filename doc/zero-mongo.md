# Enable mongo-db

## 1. Configuration

In your class path:

vertx.yml

```yaml
zero:
  lime: mongo	# Mongo Enable
  vertx:
    instance:
    - name: vx-zero
      options:
        maxEventLoopExecuteTime: 30000000000
```

vertx-mongo.yml

```yaml
db_name: vertx_zero_up
port: 27017
host: 127.0.0.1
```

*: Here ignored sender code, the mongo db only supported in consumer class in standard way.

## 2. Consumer Code

```java
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import javax.inject.infix.Mongo;
import io.vertx.up.atom.Envelop;
import io.vertx.zero.tool.Jackson;

@Queue
public class UserWorker {

    @Mongo
    private transient MongoClient client;

    @Address("ZERO://ROLE")
    public void async(final Message<Envelop> message) {
        final User user = Envelop.data(message, User.class);
        final JsonObject userData = new JsonObject(Jackson.serialize(user));
        this.client.save("DB_USER", userData, res -> {
            if (res.succeeded()) {
                message.reply(Envelop.success("Hello World"));
            } else {
                res.cause().printStackTrace();
            }
        });
    }
}
```
