package up.god.apollo.exp4;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Authorize;
import io.vertx.up.annotations.Wall;
import io.vertx.up.plugin.mongo.MongoInfix;
import io.vertx.up.secure.handler.BasicOstium;

@Wall("mongo")
public class MongoKeeper {

    @Authenticate
    public AuthHandler authenticate(final JsonObject config) {
        return BasicOstium.create(
                MongoAuth.create(MongoInfix.getClient(), config)
        );
    }

    @Authorize
    public boolean authorize(final User user) {
        System.out.println(user);
        return true;
    }
}
