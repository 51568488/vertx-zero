package io.vertx.zero.ce;

import com.vie.cv.Strings;
import com.vie.hoc.HJson;
import com.vie.hoc.HNull;
import com.vie.hoc.HPool;
import com.vie.exception.ZeroException;
import com.vie.core.reliable.Insurer;
import com.vie.core.reliable.RequiredInsurer;
import com.vie.core.reliable.TypedInsurer;
import com.vie.log.Annal;
import com.vie.util.Instance;
import com.vie.util.Types;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Ruler checking for json object / json array
 */
public class Ruler {

    private static final Annal LOGGER = Annal.get(Ruler.class);

    private static final String RULE_PATH = "ke/rules/{0}.yml";

    private static final ConcurrentMap<String, JsonObject> RULE_MAP =
            new ConcurrentHashMap<>();

    /**
     * Verify data for each file
     *
     * @param file
     * @param data
     * @throws ZeroException
     */
    public static void verify(
            final String file,
            final JsonObject data)
            throws ZeroException {
        HNull.execZero(() -> {
            // 1. Rule for json object
            final JsonObject rule = getRule(file);
            verifyItem(data, rule);
            // 2. For json item
            for (final String field : data.fieldNames()) {
                final Object value = data.getValue(field);
                HNull.execZero(() -> {
                    if (Types.isJObject(value) || Types.isJArray(value)) {
                        final String filename = file + Strings.DOT + field;
                        if (Types.isJObject(value)) {
                            // 3.1.2 Json array child
                            verify(filename, (JsonObject) value);
                        } else if (Types.isJArray(value)) {
                            // 3.1.3 Json array child
                            verify(filename, (JsonArray) value);
                        }
                    }
                }, value);
            }
        }, file, data);
    }

    /**
     * Verify data for each file
     *
     * @param file
     * @param data
     * @throws ZeroException
     */
    public static void verify(
            final String file,
            final JsonArray data)
            throws ZeroException {
        HNull.execZero(() -> {
            // 1. Rule for json array
            final JsonObject rule = getRule(file);
            verifyItem(data, rule);
            // 2. For json item
            HJson.execZero(data, JsonObject.class, (element, index) -> {
                // 3. Each item
                HJson.execZero(element, (value, field) -> {
                    // 4.Value = JsonObject, identify if extension.
                    final String filename = file + Strings.DOT + field;
                    if (Types.isJObject(value)) {
                        // JsonObject
                        verify(filename, (JsonObject) value);
                    } else if (Types.isJArray(value)) {
                        // JsonArray
                        verify(filename, (JsonArray) value);
                    }
                });
            });
        }, file, data);
    }

    /**
     * JsonObject
     *
     * @param input
     * @param rule
     * @throws ZeroException
     */
    private static <T> void verifyItem(final T input, final JsonObject rule)
            throws ZeroException {
        HNull.execZero(() -> {
            if (Types.isJArray(input)) {
                final JsonArray data = (JsonArray) input;
                // Required
                Insurer reference = Instance.singleton(RequiredInsurer.class);
                reference.flumen(data, rule);
                // Typed
                reference = Instance.singleton(TypedInsurer.class);
                reference.flumen(data, rule);
            } else {
                final JsonObject data = (JsonObject) input;
                // Required
                Insurer reference = Instance.singleton(RequiredInsurer.class);
                reference.flumen(data, rule);
                // Typed
                reference = Instance.singleton(TypedInsurer.class);
                reference.flumen(data, rule);
            }
        }, input, rule);
    }

    private static JsonObject getRule(final String file) {
        // Cached rule into memory pool
        final String filename = MessageFormat.format(RULE_PATH, file);
        if (RULE_MAP.containsKey(filename)) {
            LOGGER.info(Message.RULE_CACHED_FILE, filename);
        } else {
            LOGGER.info(Message.RULE_FILE, filename);
        }
        return HPool.exec(RULE_MAP, filename, () -> IO.getYaml(filename));
    }
}
