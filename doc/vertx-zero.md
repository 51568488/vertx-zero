# Getting Start with Vert.x-Zero

## 1. POJO Model

```java
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String name;
    private String email;
    private String qq;
    private String mobile;
    private int age;
    private Date birthday;
    private Date updated;
}
```

## 2. Agent Thread ( Event Loop )

```java

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import org.vie.util.Jackson;
import org.vie.util.log.Annal;

import javax.ws.rs.*;
import java.util.Date;

/**
 *
 */
@Path("/zero/exmaple")
@EndPoint
public class UserAgent {

    private static final Annal LOGGER = Annal.get(UserAgent.class);
    private static final String INPUT = "[ ZERO ] ( Exmaple ) arguments {0}, " +
            "type = {1}, response = {2}";

    /**
     * Block one way
     *
     * @param name
     */
    @GET
    @Path("/block/{name}")
    public void blockRequest(
            @PathParam("name") final String name) {
        LOGGER.info(INPUT, "name = " + name, "Path", "VOID");
    }

    /**
     * Sync request ( return String )
     *
     * @param email
     * @return
     */
    @GET
    @Path("/sync/string")
    public String syncRequest(
            @QueryParam("email") final String email
    ) {
        final String response = "Testing finished";
        LOGGER.info(INPUT, "email = " + email, "Query", response);
        return response;
    }

    /**
     * Sync request ( return int )
     *
     * @param age
     * @return
     */
    @GET
    @Path("/sync/integer")
    public int syncRequest(
            @QueryParam("age") final int age
    ) {
        final int response = 100;
        LOGGER.info(INPUT, "age = " + age, "Query", response);
        return response;
    }

    /**
     * Sync request ( return User )
     *
     * @return
     */
    @GET
    @Path("/sync/object")
    public User syncRequest() {
        final User user = new User();
        user.setAge(100);
        user.setBirthday(new Date());
        user.setEmail("lang.yu@hpe.com");
        user.setMobile("15922611447");
        user.setQq("445191171");
        user.setUpdated(new Date());
        return user;
    }

    /**
     * Async Request ( User will be passed in event bus )
     *
     * @return
     */
    @POST
    @Path("/one-way/user")
    @Address("ZERO://USER")
    public String sendNotify(
            @BodyParam final User user) {
        final String response = Jackson.serialize(user);
        LOGGER.info(INPUT, "User = " + user.toString(), "Body", response);
        return response;
    }
}
```

## 3. Worker Thread ( Worker Pool )

Above method contains a method as following:

```java
    /**
     * Async Request ( User will be passed in event bus )
     *
     * @return
     */
    @POST
    @Path("/one-way/user")
    @Address("ZERO://USER")
    public String sendNotify(
            @BodyParam final User user) {
        final String response = Jackson.serialize(user);
        LOGGER.info(INPUT, "User = " + user.toString(), "Body", response);
        return response;
    }
```

It means that the agent thread will call this method and send the message that wrapper the returned value to event bus `ZERO://USER`, so we need to prepare a worker as following:

```java

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.ce.Envelop;

@Queue
public class UserWorker {

    @Address("ZERO://USER")
    public Envelop reply(final Envelop message) {
        final User user = message.data(User.class);
        System.out.println(user);
        return Envelop.success(user);
    }
}
```

Now the worker only support the definition as `Envelop <name>( Envelop )`, it's defined by us, but we could get the data information from Envelop.

## 4. Main function

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

## 5. Start Application

You should see following output in console

```
[ ZERO ] ( 1 EndPoint ) The Zero system has found 1 components of @EndPoint.
[ ZERO ] ( 5 Event ) The endpoint org.tlk.api.UserAgent scanned 5 events of Event, will be mounted to routing system.
[ ZERO ] ( 1 Queue ) The Zero system has found 1 components of @Queue.
[ ZERO ] Vert.x zero has found 1 incoming address from the system. Incoming address list as below: 
[ ZERO ]        Addr : ZERO://USER
[ ZERO ] ( 1 Receipt ) The queue org.tlk.api.UserWorker scanned 1 records of Receipt, will be mounted to event bus.
......
[ ZERO ] ( Uri Register ) "/zero/exmaple/one-way/user" has been deployed by ZeroHttpAgent,...
[ ZERO ] ( Uri Register ) "/zero/exmaple/sync/object" has been deployed by ZeroHttpAgent...
[ ZERO ] ( Uri Register ) "/zero/exmaple/block/:name" has been deployed by ZeroHttpAgent...
[ ZERO ] ( Uri Register ) "/zero/exmaple/sync/integer" has been deployed by ZeroHttpAgent...
[ ZERO ] ( Uri Register ) "/zero/exmaple/sync/string" has been deployed by ZeroHttpAgent...
ZeroHttpAgent Http Server has been started successfully. Endpoint: http://0.0.0.0:8083/.
```

Then you can test with rest client tool to fetch data from 8083 port.

```
Test URI: http://localhost:8083/zero/example/one-way/user
Test Method: POST
Input:
{
	"name":"lang",
	"email":"lang.yu@hpe.com"
}
Response:
{
    "brief": "OK",
    "status": 200,
    "data": {
        "name": "lang",
        "email": "lang.yu@hpe.com",
        "qq": null,
        "mobile": null,
        "age": 0,
        "birthday": null,
        "updated": null
    }
}
```

