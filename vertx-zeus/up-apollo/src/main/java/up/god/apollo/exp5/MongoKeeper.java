package up.god.apollo.exp5;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.annotations.Phase;
import io.vertx.up.annotations.Wall;
import io.vertx.up.eon.em.AuthPhase;

@Wall(value = "mongo", path = "/exp5/*", define = true)
public class MongoKeeper {

    @Phase(AuthPhase.HEADER)
    public JsonObject parse(final MultiMap headers) {
        // TODO: Parsing Header
        return new JsonObject();
    }

    @Phase(AuthPhase.AUTHORIZE)
    public void authorize(final JsonObject authInfo,
                          final Handler<AsyncResult<User>> resultHandler) {
        // TODO: Authorize
    }

    @Phase(AuthPhase.ACCESS)
    public User access(final String authority,
                       final Handler<AsyncResult<Boolean>> resultHandler) {
        // TODO: Access
        return null;
    }
}
