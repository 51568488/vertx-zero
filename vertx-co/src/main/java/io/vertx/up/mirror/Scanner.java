package io.vertx.up.mirror;

import com.vie.cv.FileTypes;
import com.vie.cv.Protocols;
import com.vie.cv.Strings;
import com.vie.cv.Values;
import com.vie.hoc.HFail;
import com.vie.hoc.HNull;
import com.vie.log.Annal;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Scan the package to extract classes.
 */
public final class Scanner {

    private static final Annal LOGGER = Annal.get(Scanner.class);

    public static Set<Class<?>> getClasses(final String... zeroScans) {
        final Set<Class<?>> all = new HashSet<>();
        for (final String scan : zeroScans) {
            all.addAll(getClasses(scan));
        }
        return all;
    }

    private static Set<Class<?>> getClasses(final String zeroScan) {
        // The first class collection
        final Set<Class<?>> classes = new LinkedHashSet<>();
        return HNull.get(() -> {
            // Recurisive
            final boolean recursive = true;
            // Get package name;
            final String packageDir = zeroScan.replace(Strings.DOT, Strings.SLASH);
            HFail.exec(() -> {
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
                        findAndAdd(zeroScan, path, recursive, classes);
                    } else {
                        classes.addAll(getClasses(packageDir, zeroScan, url, recursive));
                    }
                }
                return null;
            }, LOGGER);
            return classes;
        }, zeroScan);
    }

    private static Set<Class<?>> getClasses(final String packageDir,
                                            final String packName,
                                            final URL url,
                                            final boolean recursive) {
        final Set<Class<?>> classes = new LinkedHashSet<>();
        HFail.exec(() -> {
            String packageName = packName;
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
                        if (name.endsWith(Strings.DOT + FileTypes.CLASS) && !entry.isDirectory()) {
                            // Extract class Name
                            final String className = name.substring(packageName.length() + 1, name.length() - 6);
                            final String injectPkg = packageName;
                            HFail.exec(() -> {
                                classes.add(Class.forName(injectPkg + '.' + className));
                                return null;
                            }, className);
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
        // Scan all files
        HNull.exec(() -> {
            for (final File classFile : dirfiles) {
                // If directory, continue
                if (file.isDirectory()) {
                    findAndAdd(packName + Strings.DOT + file.getName(),
                            file.getAbsolutePath(),
                            recursive, classesRef);
                } else {
                    // If java, remove .class from name
                    final String className = classFile.getName().substring(0, classFile.getName().length() - 6);
                    HFail.exec(() -> {
                        // Add into collection
                        classesRef.add(Thread
                                .currentThread().getContextClassLoader()
                                .loadClass(packName + Strings.DOT + className));
                        return null;
                    }, className);
                }
            }
        }, dirfiles);
    }

    static class ClassFileFilter implements FileFilter {
        private transient boolean recursive = true;

        private ClassFileFilter() {
            this(true);
        }

        private ClassFileFilter(final boolean recursive) {
            this.recursive = recursive;
        }

        @Override
        public boolean accept(final File file) {
            return (this.recursive && file.isDirectory())
                    || (file.getName().endsWith(Strings.DOT + FileTypes.CLASS));
        }
    }

    private Scanner() {
    }
}
