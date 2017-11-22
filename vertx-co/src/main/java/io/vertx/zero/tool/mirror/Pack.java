package io.vertx.zero.tool.mirror;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Protocols;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;
import zava.io.ClassFileFilter;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * Pack the package to extract classes.
 */
public final class Pack {

    private static final Annal LOGGER = Annal.get(Pack.class);

    private static final Set<Class<?>> CLASSES = new ConcurrentHashSet<>();

    public static Set<Class<?>> getClasses(final Predicate<Class<?>> filter,
                                           final String... zeroScans) {
        if (CLASSES.isEmpty()) {
            if (0 < zeroScans.length) {
                for (final String scan : zeroScans) {
                    CLASSES.addAll(getClasses(filter, scan));
                }
            } else {
                CLASSES.addAll(getClasses(filter, Strings.DOT));
            }
        }
        return CLASSES;
    }

    private static Set<Class<?>> getClasses(final Predicate<Class<?>> filter,
                                            final String zeroScan) {
        // The first class collection
        final Set<Class<?>> classes = new LinkedHashSet<>();
        return Fn.get(() -> {
            // Recurisive
            final boolean recursive = true;
            // Get package name;
            final String packageDir = (Strings.DOT.equals(zeroScan)) ?
                    zeroScan.replace(Strings.DOT, Strings.EMPTY)
                    : zeroScan.replace(Strings.DOT, Strings.SLASH);
            Fn.getJvm(() -> {
                // Define enumeration
                final Enumeration<URL> dirs = Thread.currentThread()
                        .getContextClassLoader().getResources(packageDir);
                // While loop
                while (dirs.hasMoreElements()) {
                    // Next element
                    final URL url = dirs.nextElement();
                    // Protocols
                    final String protocol = url.getProtocol();
                    if (Protocols.FILE.equals(protocol)) {
                        // Get path of this package
                        final String path = URLDecoder.decode(url.getFile(), Values.ENCODING);
                        // Call find and add
                        findAndAdd(packageDir, path, recursive, classes);
                    } else if (Protocols.JAR.equals(protocol)) {
                        classes.addAll(getClasses(packageDir, zeroScan, url, recursive));
                    }
                }
                // No Return
                return null;
            }, LOGGER);
            return Fn.getSemi(null == filter, LOGGER,
                    () -> classes,
                    () -> classes.stream().filter(filter).collect(Collectors.toSet()));
        }, zeroScan);
    }

    private static Set<Class<?>> getClasses(final String packageDir,
                                            final String packName,
                                            final URL url,
                                            final boolean recursive) {
        final Set<Class<?>> classes = new LinkedHashSet<>();
        Fn.getJvm(() -> {
            String packageName = (packName.startsWith(Strings.DOT)) ?
                    packName.substring(1, packName.length()) :
                    packName;
            // Get jar file
            final JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
            // List all entries of this jar
            final Enumeration<JarEntry> entries = jar.entries();
            // Loop for jar entry.
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                String name = entry.getName();
                // Start with /
                if (name.charAt(0) == '/') {
                    name = name.substring(1);
                }
                // The same with package dir
                if (name.startsWith(packageDir)) {
                    final int idx = name.lastIndexOf('/');
                    // end with /, it's package.
                    if (idx != -1) {
                        packageName = name.substring(0, idx).replace('/', '.');
                    }
                    if ((idx != -1) || recursive) {
                        // .class and not directory
                        if (name.endsWith(Strings.DOT + FileSuffix.CLASS) && !entry.isDirectory()) {
                            // Extract class Name
                            final String className = name.substring(packageName.length() + 1, name.length() - 6);
                            try {
                                classes.add(Class.forName(packageName + Strings.DOT + className));
                            } catch (final Throwable ex) {
                                // Skip
                                LOGGER.info(ex.getMessage());
                            }
                        }
                    }
                }
            }
            return null;
        }, packageDir, packName, url);
        return classes;
    }


    private static void findAndAdd(
            final String packName,
            final String packPath,
            final boolean recursive,
            final Set<Class<?>> classesRef
    ) {
        // Read the folder of current packaqge
        final File file = new File(packPath);
        // If it does not exist, return directly
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        // If exist, list all files include directory
        final File[] dirfiles = file.listFiles(new ClassFileFilter());
        // Pack all files
        final String packageName = (packName.startsWith(Strings.DOT)) ?
                packName.substring(1, packName.length()) :
                packName;
        Fn.safeNull(() -> {
            for (final File classFile : dirfiles) {
                // If directory, continue
                if (classFile.isDirectory()) {
                    findAndAdd(packageName + Strings.DOT + classFile.getName(),
                            classFile.getAbsolutePath(),
                            recursive, classesRef);
                } else {
                    // If java, remove .class from name
                    final String className = classFile.getName().substring(0, classFile.getName().length() - 6);
                    try {
                        // Add into collection
                        classesRef.add(Thread
                                .currentThread().getContextClassLoader()
                                .loadClass(packageName + Strings.DOT + className));
                    } catch (final Throwable ex) {
                        LOGGER.info(ex.getMessage());
                    }
                }
            }
        }, dirfiles);
    }

    private Pack() {
    }
}
