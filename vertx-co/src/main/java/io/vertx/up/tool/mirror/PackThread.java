package io.vertx.up.tool.mirror;

import io.vertx.core.impl.ConcurrentHashSet;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Package scan thread
 */
public class PackThread extends Thread {

    private final transient String pkg;
    private final transient Predicate<Class<?>> filter;
    private final Set<Class<?>> classes = new ConcurrentHashSet<>();

    public PackThread(final String pkg,
                      final Predicate<Class<?>> filter) {
        this.setName("package-scanner-" + super.getId());
        this.pkg = pkg;
        this.filter = filter;
    }

    @Override
    public void run() {
        final Set<Class<?>> clazzes = PackScanner.getClasses(this.filter, this.pkg);
        this.classes.addAll(clazzes);
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}
