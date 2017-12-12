package io.vertx.up.rs.hunt;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Rule;
import io.vertx.up.eon.ID;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400ValidationException;
import io.vertx.up.func.Fn;
import io.vertx.up.web.ZeroCodex;
import io.vertx.zero.eon.Strings;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import javax.ws.rs.BodyParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Validator {

    private static final javax.validation.Validator VALIDATOR
            = Validation.buildDefaultValidatorFactory().getValidator();

    private static final ConcurrentMap<String, Map<String, List<Rule>>>
            RULERS = new ConcurrentHashMap<>();

    private static Validator INSTANCE;

    public static Validator create() {
        if (null == INSTANCE) {
            synchronized (Validator.class) {
                if (null == INSTANCE) {
                    INSTANCE = new Validator();
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

    public Map<String, List<Rule>> buildRulers(
            final Depot depot) {
        final Map<String, List<Rule>> rulers
                = new LinkedHashMap<>();
        final ConcurrentMap<String, Class<? extends Annotation>>
                annotions = depot.getAnnotations();
        Observable.fromIterable(annotions.keySet())
                .filter(ID.DIRECT::equals)
                .map(annotions::get)
                // 1. Check whether contains @BodyParam
                .any(item -> BodyParam.class == item)
                // 2. Build rulers
                .map(item -> buildKey(depot.getEvent()))
                .map(this::buildRulers)
                .subscribe(rulers::putAll);
        return rulers;
    }

    private Map<String, List<Rule>> buildRulers(final String key) {
        if (RULERS.containsKey(key)) {
            return RULERS.get(key);
        } else {
            final JsonObject rule = ZeroCodex.getCodex(key);
            final Map<String, List<Rule>> ruler
                    = new LinkedHashMap<>();
            if (null != rule) {
                Fn.itJObject(rule, (value, field) -> {
                    // Checked valid rule config
                    final List<Rule> rulers = buildRulers(value);
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

    private List<Rule> buildRulers(final Object config) {
        final List<Rule> rulers = new ArrayList<>();
        if (null != config && config instanceof JsonArray) {
            final JsonArray configData = (JsonArray) config;
            Fn.itJArray(configData, JsonObject.class, (item, index) -> {
                final Rule ruler = Rule.create(item);
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
