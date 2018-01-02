package io.vertx.up.tool.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;
import io.vertx.zero.eon.em.YamlType;
import io.vertx.zero.exception.heart.EmptyStreamException;
import io.vertx.zero.exception.heart.JsonFormatException;
import io.vertx.zero.log.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * The library for IO resource reading.
 */
public final class IO {
    /**
     * Yaml
     **/
    private static final ObjectMapper YAML = new YAMLMapper();

    /**
     * Direct read by vert.x logger to avoid dead lock
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(IO.class);

    /**
     * Read to JsonArray
     *
     * @param filename The filename to describe source path
     * @return Return to JsonArray object
     */
    public static JsonArray getJArray(final String filename) {
        return Fn.transRun(() -> new JsonArray(getString(filename)),
                JsonFormatException.class, filename);
    }

    /**
     * Read to JsonObject
     *
     * @param filename The filename to describe source path
     * @return Return to JsonObject
     */
    public static JsonObject getJObject(final String filename) {
        final String content = getString(filename);
        // TODO: For debug
        return Fn.transRun(() -> new JsonObject(content),
                JsonFormatException.class, filename);
    }

    /**
     * Read to String
     *
     * @param in
     * @return
     */
    public static String getString(final InputStream in) {
        final StringBuilder buffer = new StringBuilder(Values.BUFFER_SIZE);
        return Fn.getJvm(() -> {
            final BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(in, Values.ENCODING)
                    );
            // Character stream
            String line;
            while (null != (line = reader.readLine())) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        }, in);
    }

    public static String getString(final String filename) {
        return Fn.getJvm(
                () -> getString(Stream.read(filename)), filename);
    }

    /**
     * Read yaml to JsonObject
     *
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getYaml(final String filename) {
        final YamlType type = getYamlType(filename);
        return Fn.getSemi(YamlType.ARRAY == type, null,
                () -> (T) Fn.getJvm(() -> new JsonArray(
                        getYamlNode(filename).toString()
                ), filename),
                () -> (T) Fn.getJvm(() -> new JsonObject(
                        getYamlNode(filename).toString()
                ), filename));
    }

    private static JsonNode getYamlNode(final String filename) {
        final InputStream in = Stream.read(filename);
        final JsonNode node = Fn.safeJvm(() -> {
            if (null == in) {
                throw new EmptyStreamException(filename);
            }
            return YAML.readTree(in);
        }, null);
        if (null == node) {
            throw new EmptyStreamException(filename);
        }
        return node;
    }

    /**
     * Check yaml type
     *
     * @param filename
     * @return
     */
    public static YamlType getYamlType(final String filename) {
        final String content = IO.getString(filename);
        return Fn.get(YamlType.OBJECT, () -> {
            if (content.trim().startsWith(Strings.DASH)) {
                return YamlType.ARRAY;
            } else {
                return YamlType.OBJECT;
            }
        }, content);
    }

    /**
     * Read to property object
     *
     * @param filename
     * @return
     */
    public static Properties getProp(final String filename) {
        return Fn.getJvm(() -> {
            final Properties prop = new Properties();
            final InputStream in = Stream.read(filename);
            prop.load(in);
            in.close();
            return prop;
        }, filename);
    }

    /**
     * Read to URL
     *
     * @param filename
     * @return
     */
    public static URL getURL(final String filename) {
        return Fn.getJvm(() -> {
            final URL url = Thread.currentThread().getContextClassLoader()
                    .getResource(filename);
            return Fn.getSemi(null == url, null,
                    () -> IO.class.getResource(filename),
                    () -> url);
        }, filename);
    }

    /**
     * Read to File
     *
     * @param filename
     * @return
     */
    public static File getFile(final String filename) {
        return Fn.getJvm(() -> {
            final File file = new File(filename);
            return Fn.getSemi(file.exists(), null,
                    () -> file,
                    () -> {
                        final URL url = getURL(filename);
                        if (null == url) {
                            throw new EmptyStreamException(filename);
                        }
                        return new File(url.getFile());
                    });
        }, filename);
    }

    /**
     * Read to Path
     *
     * @param filename
     * @return
     */
    public static String getPath(final String filename) {
        return Fn.getJvm(() -> {
            final File file = getFile(filename);
            return Fn.getJvm(() -> {
                Log.info(LOGGER, Info.INF_APATH, file.getAbsolutePath());
                return file.getAbsolutePath();
            }, file);
        }, filename);
    }

    private IO() {
    }
}
