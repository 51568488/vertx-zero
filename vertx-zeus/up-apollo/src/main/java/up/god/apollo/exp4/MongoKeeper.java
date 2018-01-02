package up.god.apollo.exp4;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Authorize;
import io.vertx.up.annotations.Wall;

@Wall("mongo")
public class MongoKeeper {

    @Authenticate
    public User authenticate(final JsonObject input) {
        System.out.println(input);
        return null;
    }

    @Authorize
    public boolean authorize(final User user) {
        System.out.println(user);
        return true;
    }
}
