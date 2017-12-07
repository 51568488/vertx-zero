package io.vertx.up.tool.mirror;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.log.Annal;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;

/**
 * Pack the package to extract classes.
 */
public final class Pack {

    private static final Annal LOGGER = Annal.get(Pack.class);

    private static final Set<Class<?>> CLASSES = new ConcurrentHashSet<>();

    private static final Set<String> FORBIDDEN = new HashSet<String>() {
        {
            add("java");
            add("javax");
            add("jdk");
            // Sax & Yaml
            add("org.xml");
            add("org.yaml");
            // Idea
            add("com.intellij");
            // Sun
            add("sun");
            add("com.sun");
            // Netty
            add("io.netty");
            // Jackson
            add("com.fasterxml");
            // Logback
            add("ch.qos");
            add("org.slf4j");
            // Asm
            add("org.ow2");
            add("org.objectweb");
            add("com.esotericsoftware");
            // Hazelcast
            add("com.hazelcast");
            // Vert.x core
            add("io.vertx.core");
            add("io.vertx.spi");
        }
    };

    public static Set<Class<?>> getClasses(final Predicate<Class<?>> filter,
                                           final String... zeroScans) {
        if (CLASSES.isEmpty()) {
            if (0 < zeroScans.length) {
                CLASSES.addAll(collectClasses(zeroScans, filter));
            } else {
                final Package[] packages = Package.getPackages();
                final Set<String> packageDirs = new HashSet<>();
                for (final Package pkg : packages) {
                    final String pending = pkg.getName();
                    final boolean skip = FORBIDDEN.stream().anyMatch(pending::startsWith);
                    if (!skip) {
                        packageDirs.add(pending);
                    }
                }
                LOGGER.info(Info.PACKAGES, String.valueOf(packageDirs.size()),
                        String.valueOf(packages.length));
                CLASSES.addAll(collectClasses(packageDirs.toArray(new String[]{}), filter));
            }
        }
        return CLASSES;
    }

    private static Set<Class<?>> collectClasses(final String[] packageDir, final Predicate<Class<?>> filter) {
        // Counter
        final CountDownLatch counter = new CountDownLatch(packageDir.length);
        final Set<PackThread> references = new HashSet<>();
        for (final String pkgName : packageDir) {
            final PackThread thread = new PackThread(pkgName, filter, counter);
            references.add(thread);
            thread.start();
        }
        // Wait
        final Set<Class<?>> result = new HashSet<>();
        try {
            counter.await();
            // Collect all the classes
            for (final PackThread thread : references) {
                result.addAll(thread.getClasses());
            }
        } catch (final InterruptedException ex) {
            LOGGER.jvm(ex);
        }
        return result;
    }

    private Pack() {
    }
}
