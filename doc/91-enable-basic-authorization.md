# Enable Basic Authorization

You can enable authorization in zero system as following steps:

## 1. Configuration

In your configuration `vertx.yml`, you must define lime extend node as following:

```yaml
zero:
  lime: secure
```

Then it means that you must create new file named `vertx-secure.yml` instead with following content:

```yaml
secure:
  # Standard Type
  mongo:
    type: mongo
    config:
      collectionName: DB_USER
      saltStyle: NO_SALT
```

Zero system provide some standard authorization by type \( Now support **mongo** \).

## 2. Create new class

Then you can create new class as following:

```java
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.plugin.mongo.MongoInfix;
import io.vertx.up.secure.handler.BasicOstium;

@Wall(value = "mongo", path = "/exp4/*")
public class MongoKeeper {

    @Authenticate
    public AuthHandler authenticate(final JsonObject config) {
        return BasicOstium.create(
                MongoAuth.create(MongoInfix.getClient(), config)
        );
    }
}
```



