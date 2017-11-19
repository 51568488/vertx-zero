package org.tlk.api;

import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.log.Annal;
import io.vertx.zero.tool.Jackson;

import javax.ws.rs.*;
import java.util.Date;

/**
 *
 */
@Path("/zero")
@EndPoint
public class UserApi {

    private static final Annal LOGGER = Annal.get(UserApi.class);
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

    @POST
    @Path("/async/user")
    @Address("ZERO://ROLE")
    public String sendAsync(
            @BodyParam final User user) {
        final String response = Jackson.serialize(user);
        LOGGER.info(INPUT, "( Mongo ) User = " + user.toString(),
                "Body", response);
        return response;
    }
}
