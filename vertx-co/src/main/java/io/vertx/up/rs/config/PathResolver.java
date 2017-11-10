package io.vertx.up.rs.config;

import org.vie.cv.Strings;
import org.vie.cv.Values;
import org.vie.exception.up.PathAnnoEmptyException;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.util.StringUtil;
import org.vie.util.log.Annal;

import javax.ws.rs.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Path resolver
 * 1. Root ( Class ) + Context ( Method )
 * 2. Context ( Method )
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
        HBool.execUp(null == path, LOGGER,
                PathAnnoEmptyException.class, PathResolver.class);
        // Calculate single path
        return resolve(path, null);
    }

    /**
     * Parse the api endpoint for @Path ( Method Level )
     *
     * @param path
     * @param root
     * @return
     */
    public static String resolve(final Path path, final String root) {
        HBool.execUp(null == path, LOGGER,
                PathAnnoEmptyException.class, PathResolver.class);
        return HBool.exec(StringUtil.isNil(root), () -> calculate(path.value()),
                () -> {
                    final String api = calculate(root);
                    final String contextPath = calculate(path.value());
                    // If api has been calculated to
                    if (Values.ONE == api.length()) {
                        return path(contextPath);
                    } else {
                        return path(api + contextPath);
                    }
                });
    }

    /**
     * JSR311: /query/{name}
     * Named: /query/:name ( Vertx Format )
     *
     * @param path
     * @return
     */
    private static String path(final String path) {
        final String regex = "\\{\\w+\\}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(path);
        String tempStr = path;
        String result = "";
        while (matcher.find()) {
            result = matcher.group();
            // Shift left brace and right brace
            final String replaced = result.trim().substring(1, result.length() - 1);
            tempStr = tempStr.replace(result, ":" + replaced);
        }
        return tempStr;
    }

    /**
     * Calculate the path
     * 1. Remove the last '/';
     * 2. Append the '/' to first;
     * 3. Replaced all duplicated '//';
     *
     * @param path
     * @return
     */
    private static String calculate(final String path) {
        String uri = path;
        // 1. Shift the SLASH: Multi -> Single one.
        uri = uri.replaceAll("\\/+", Strings.SLASH);
        // 1. Remove the last SLASH
        if (uri.endsWith(Strings.SLASH)) {
            uri = uri.substring(0, uri.lastIndexOf(Strings.SLASH));
        }
        // Uri must start with SLASH
        final String processed = uri;
        return HNull.get(() -> HBool.exec(processed.startsWith(Strings.SLASH),
                () -> processed,
                () -> Strings.SLASH + processed),
                uri);
    }
}
