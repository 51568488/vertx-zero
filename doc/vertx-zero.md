# Getting Start with Vert.x-Zero

## 1. Write Handler

```java
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@Path("/test")
@EndPoint
public class User {
	// Query String parameters
    @GET
    @Path("/{name}")
    public String testPath(@PathParam("name") final String name) {
        System.out.println(name);
        return "Hello World";
    }
    
	// JsonObject body parameters
    @POST
    @Path("/lang")
    public Model testBodyReturn(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("silentbalanceyh@126.com");
        user.setName("Lang.yu");
        return user;
    }
	// Event Bus with Body parameters
    @POST
    @Path("/message")
    @Address("ZERO://USER")
    public Model testEvent(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("silentbalanceyh@126.com");
        user.setName("Lang.yu");
        return user;
    }
}
```

Model is only a sample POJO.

```java
public class Model {

    private String name;
    private String email;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
```

## 2. Main function

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

## 3. Start Application

You should see following output in console

```
[ZERO-VTC] ( URI MAPPING ) "/test/lang" has been deployed by ZeroHttpAgent, .....
[ZERO-VTC] ( URI MAPPING ) "/test/message" has been deployed by ZeroHttpAgent, ....
[ZERO-VTC] ( URI MAPPING ) "/test/:name" has been deployed by ZeroHttpAgent, ....
[ZERO-VTC] ZeroHttpAgent Http Server has been started successfully. Endpoint: http://0.0.0.0:8083/.
```

Then you can test with rest client tool to fetch data from 8083 port.

