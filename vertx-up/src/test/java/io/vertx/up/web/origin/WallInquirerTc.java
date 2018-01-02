package io.vertx.up.web.origin;

import io.vertx.quiz.ScanBase;
import io.vertx.quiz.example.WallKeeper2;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.zero.exception.DuplicatedWallException;
import io.vertx.up.tool.mirror.Instance;
import org.junit.Test;

import java.util.Set;

public class WallInquirerTc extends ScanBase {

    private final Inquirer<Set<Cliff>> walls =
            Instance.singleton(WallInquirer.class);

    @Test(expected = DuplicatedWallException.class)
    public void testScan() {
        final Set<Class<?>> classes = getClasses();
        this.walls.scan(classes);
    }

    @Test
    public void testScanCorrect() {
        final Set<Class<?>> classes = getClasses();
        classes.remove(WallKeeper2.class);
        this.walls.scan(classes);
    }
}
