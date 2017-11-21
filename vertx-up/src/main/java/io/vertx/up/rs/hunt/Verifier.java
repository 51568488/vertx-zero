package io.vertx.up.rs.hunt;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Ruler;
import io.vertx.up.eon.ID;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400ValidationException;
import io.vertx.up.func.Fn;
import io.vertx.up.web.ZeroCodex;
import io.vertx.zero.eon.Strings;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.ws.rs.BodyParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Verifier {

    private static final Validator VALIDATOR
            = Validation.buildDefaultValidatorFactory().getValidator();

    private static final ConcurrentMap<String, ConcurrentMap<String, List<Ruler>>>
            RULERS = new ConcurrentHashMap<>();

    private static Verifier INSTANCE;

    public static Verifier create() {
        if (null == INSTANCE) {
            synchronized (Verifier.class) {
                if (null == INSTANCE) {
                    INSTANCE = new Verifier();
                }
            }
        }
        return INSTANCE;
    }

    public <T> void verifyMethod(
            final T proxy,
            final Method method,
            final Object[] args) {
        // 1. Get method validator
        final ExecutableValidator validatorParam
                = VALIDATOR.forExecutables();
        // 2. Create new params that wait for validation
        final Set<ConstraintViolation<T>> constraints
                = validatorParam.validateParameters(proxy, method, args);
        // 3. System.out.println
        for (final ConstraintViolation<T> item : constraints) {
            final WebException error
                    = new _400ValidationException(getClass(),
                    proxy.getClass(), method, item.getMessage());
            error.setReadible(item.getMessage());
            throw error;
        }
    }

    public ConcurrentMap<String, List<Ruler>> buildRulers(
            final Depot depot) {
        final ConcurrentMap<String, List<Ruler>> rulers
                = new ConcurrentHashMap<>();
        final ConcurrentMap<String, Class<? extends Annotation>>
                annotions = depot.getAnnotations();
        if (annotions.containsKey(ID.DIRECT)) {
            // 1. Check whether contains @BodyParam
            final boolean match = annotions.values().stream()
                    .anyMatch(item -> BodyParam.class == item);
            // 2. Build rulers
            if (match) {
                final String key = buildKey(depot.getEvent());
                rulers.putAll(buildRulers(key));
            }
        }
        return rulers;
    }

    private ConcurrentMap<String, List<Ruler>> buildRulers(final String key) {
        if (RULERS.containsKey(key)) {
            return RULERS.get(key);
        } else {
            final JsonObject rule = ZeroCodex.getCodex(key);
            final ConcurrentMap<String, List<Ruler>> ruler
                    = new ConcurrentHashMap<>();
            if (null != rule) {
                Fn.itJObject(rule, (value, field) -> {
                    // Checked valid rule config
                    final List<Ruler> rulers = buildRulers(value);
                    if (!rulers.isEmpty()) {
                        ruler.put(field, rulers);
                    }
                });
                if (!ruler.isEmpty()) {
                    RULERS.put(key, ruler);
                }
            }
            return ruler;
        }
    }

    private List<Ruler> buildRulers(final Object config) {
        final List<Ruler> rulers = new ArrayList<>();
        if (null != config && config instanceof JsonArray) {
            final JsonArray configData = (JsonArray) config;
            Fn.itJArray(configData, JsonObject.class, (item, index) -> {
                final Ruler ruler = Ruler.create(item);
                if (null != ruler) {
                    rulers.add(ruler);
                }
            });
        }
        return rulers;
    }

    private String buildKey(final Event event) {
        String prefix = event.getPath().trim().substring(1);
        prefix = prefix.replace(Strings.SLASH, Strings.DOT);
        final String suffix = event.getMethod().name().toLowerCase(Locale.getDefault());
        return prefix + Strings.DOT + suffix;
    }
}
