package io.vertx.up.rs.config;

import org.vie.cv.Strings;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.util.log.Annal;

import javax.ws.rs.Path;

/**
 * Path resolver
 */
class PathResolver {

    private static final Annal LOGGER = Annal.get(PathResolver.class);

    /**
     * Parse the api endpoint for @Path ( Class Level )
     *
     * @param path
     * @return
     */
    public static String resolve(final Path path) {
        return HBool.exec(null == path,
                () -> {
                    // Null warning for path
                    LOGGER.info(Message.PATH_NULL, path);
                    return null;
                }, () -> {
                    final String uri = path.value();
                    // Uri must start with SLASH
                    return HNull.get(() -> HBool.exec(uri.startsWith(Strings.SLASH),
                            () -> uri,
                            () -> Strings.SLASH + uri)
                                    .replaceAll(Strings.SLASH + Strings.SLASH, Strings.SLASH),
                            uri);
                });
    }

    /**
     * Parse the api endpoint for @Path ( Method Level )
     *
     * @param methodPath
     * @param root
     * @return
     */
    public static String resolve(final Path methodPath, final String root) {

        return null;
    }
}
