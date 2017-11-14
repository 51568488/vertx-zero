# Parameter type supported.

The parameters ( Not belong to specific ), must be annotated with following

* `javax.ws.rs.QueryParam` ( Support )
* `javax.ws.rs.FormParam` ( In future )
* `javax.ws.rs.MatrixParam` ( In future )
* `javax.ws.rs.PathParam` ( Support )
* `javax.ws.rs.HeaderParam` ( Support )
* `javax.ws.rs.CookieParam` ( In future )
* `javax.ws.rs.BodyParam` ( Support )
* `javax.ws.rs.StreamParam` ( In future )
* `javax.ws.rs.SessionParam` ( In future )

The following parameter could be used by type without annotation ( Mustn't include )

* `io.vertx.ext.web.Session`
* `io.vertx.core.http.HttpServerRequest`
* `io.vertx.core.http.HttpServerResponse`
* `io.vertx.core.eventbus.EventBus`
* `io.vertx.core.Vertx`
* `io.vertx.ext.auth.User`
* `io.vertx.ext.web.RoutingContext`

## 1. Source Code

```java
package org.exmaple;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@Path("/up/example")
@EndPoint
public class ZeroExpApi {
    // ... Other
    @GET
    @Path("/typed/request")
    public String sayBody(
            final HttpServerRequest request
    ) {
        return request.absoluteURI();
    }
}
```

## 2. Console

```java
[ ZERO ] ( Uri Register ) "/up/example/typed/request" has been deployed by ZeroHttpAgent
```

## 3. Curl Testing

```
curl http://localhost:8083/up/example/typed/request
{"brief":"OK","status":200,"data":"http://localhost:8083/up/example/typed/request"}
```