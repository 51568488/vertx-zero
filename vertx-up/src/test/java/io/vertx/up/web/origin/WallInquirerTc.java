package io.vertx.up.web.origin;

import io.vertx.quiz.ScanBase;
import io.vertx.quiz.example.WallKeeper2;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.WallDuplicatedException;
import org.junit.Test;

import java.util.Set;

public class WallInquirerTc extends ScanBase {

    private final Inquirer<Set<Cliff>> walls =
            Instance.singleton(WallInquirer.class);

    @Test(expected = WallDuplicatedException.class)
    public void testScan() {
        final Set<Class<?>> classes = getClasses();
        this.walls.scan(classes);
    }

    @Test
    public void testScanCorrect() {
        final Set<Class<?>> classes = getClasses();
        classes.remove(WallKeeper2.class);
        final Set<Cliff> treeResult = this.walls.scan(classes);
        for (final Cliff instance : treeResult) {
            System.out.println(instance);
        }
    }
}
