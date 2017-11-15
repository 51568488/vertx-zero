package io.vertx.zero.tool.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;
import io.vertx.zero.eon.em.YamlType;
import io.vertx.zero.exception.EmptyStreamException;
import io.vertx.zero.exception.JsonFormatException;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HFail;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HTry;
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
     * @param filename
     * @return
     */
    public static JsonArray getJArray(final String filename) {
        return HTry.exec(() -> new JsonArray(getString(filename)),
                (ex) -> new JsonFormatException(filename, ex));
    }

    /**
     * Read to JsonObject
     *
     * @param filename
     * @return
     */
    public static JsonObject getJObject(final String filename) {
        final String content = getString(filename);
        // TODO: For debug
        return HTry.exec(() -> new JsonObject(content),
                (ex) -> new JsonFormatException(filename, ex));
    }

    /**
     * Read to String
     *
     * @param in
     * @return
     */
    public static String getString(final InputStream in) {
        final StringBuilder buffer = new StringBuilder(Values.BUFFER_SIZE);
        return HFail.exec(() -> {
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
        return HFail.exec(
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
        return HBool.exec(YamlType.ARRAY == type,
                () -> (T) HFail.exec(() -> new JsonArray(
                        getYamlNode(filename).toString()
                ), filename),
                () -> (T) HFail.exec(() -> new JsonObject(
                        getYamlNode(filename).toString()
                ), filename));
    }

    private static JsonNode getYamlNode(final String filename) {
        final InputStream in = Stream.read(filename);
        final JsonNode node = HTry.execGet(() -> YAML.readTree(
                HBool.exec(null != in,
                        () -> in,
                        new EmptyStreamException(filename))), null);
        return HBool.exec(null != node,
                () -> node,
                new EmptyStreamException(filename));
    }

    /**
     * Check yaml type
     *
     * @param filename
     * @return
     */
    public static YamlType getYamlType(final String filename) {
        final String content = IO.getString(filename);
        return HNull.get(content, () -> {
            if (content.trim().startsWith(Strings.DASH)) {
                return YamlType.ARRAY;
            } else {
                return YamlType.OBJECT;
            }
        }, YamlType.OBJECT);
    }

    /**
     * Read to property object
     *
     * @param filename
     * @return
     */
    public static Properties getProp(final String filename) {
        return HFail.exec(() -> {
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
        return HFail.exec(() -> {
            final URL url = Thread.currentThread().getContextClassLoader()
                    .getResource(filename);
            return HBool.exec(null == url,
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
        return HFail.exec(() -> {
            final File file = new File(filename);
            return HBool.exec(file.exists(),
                    () -> file,
                    () -> {
                        final URL url = getURL(filename);
                        return HBool.exec(null != url,
                                () -> new File(url.getFile()),
                                new EmptyStreamException(filename));
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
        return HFail.exec(() -> {
            final File file = getFile(filename);
            return HFail.exec(() -> {
                Log.info(LOGGER, Info.INF_APATH, file.getAbsolutePath());
                return file.getAbsolutePath();
            }, file);
        }, filename);
    }

    private IO() {
    }
}
