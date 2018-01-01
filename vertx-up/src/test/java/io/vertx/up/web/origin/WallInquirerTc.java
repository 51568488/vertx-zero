package io.vertx.up.web.origin;

import io.vertx.quiz.ScanBase;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.tool.mirror.Instance;
import org.junit.Test;

import java.util.Set;

public class WallInquirerTc extends ScanBase {

    private final Inquirer<Set<Cliff>> walls =
            Instance.singleton(WallInquirer.class);

    @Test
    public void testScan() {
        this.walls.scan(getClasses());
    }
}
