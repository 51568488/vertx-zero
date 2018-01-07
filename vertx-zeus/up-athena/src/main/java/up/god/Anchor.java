package up.god;

import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.ApiGateway;
import io.vertx.up.annotations.Up;

@Up
@ApiGateway
public class Anchor {

    public static void main(final String[] args) {
        VertxApplication.run(Anchor.class);
    }
}
