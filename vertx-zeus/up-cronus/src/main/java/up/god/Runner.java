package up.god;

import io.vertx.up.VertxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Runner {

    public static void main(final String[] args) {
        VertxApplication.run(Runner.class);
    }
}
