package up.god;

import io.vertx.rx.RxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Runner {

    public static void main(final String[] args) {
        RxApplication.run(Runner.class);
    }
}
