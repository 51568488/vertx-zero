package up.god;

import io.vertx.rx.RxApplication;
import io.vertx.up.annotations.Up;

@Up
public class Anchor {

    public static void main(final String[] args) {
        RxApplication.run(Anchor.class);
    }
}
