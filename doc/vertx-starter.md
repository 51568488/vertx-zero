# Simple Start with Vert.x-Zero

# 0. Environment

```xml
        <dependency>
            <groupId>io.vertx.up</groupId>
            <artifactId>vertx-dot</artifactId>
            <version>0.2-SNAPSHOT</version>
        </dependency>
```

# 1. POJO

```java
import lombok.Data;

@Data
public class Role {
    private String name;
}
```

# 2. Api

```java
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/zero")
public class RoleApi {

    @GET
    @Path("/start")
    public Role testHello(@QueryParam("name") final String name) {
        final Role role = new Role();
        role.setName(name);
        // ... Do something
        return role;
    }
}
```

# 3. Main

```java
import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Driver {

    public static void main(final String[] args) {
        VertxApplication.run(Driver.class);
    }
}
```

# 4. Run the code

In the console you should see:

```
......
[ ZERO ] ( Uri Register ) "/zero/start" has been deployed by ZeroHttpAgent, ......
......
[ ZERO ] ZeroHttpAgent Http Server has been started successfully. Endpoint: http://0.0.0.0:8083/
......
```

# 5. Test with curl

```
curl http://localhost:8083/zero/start
```
The response got from console

```
{"brief":"OK","status":200,"data":{"name":null}}
```