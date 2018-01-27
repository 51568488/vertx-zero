package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.eon.Values;
import org.jooq.Condition;
import org.jooq.Operator;
import org.jooq.impl.DSL;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

@SuppressWarnings("all")
public class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    private transient final Class<?> clazz;
    private transient final VertxDAO vertxDAO;

    <T> UxJooq(final Class<T> clazz) {
        this.clazz = clazz;
        this.vertxDAO = (VertxDAO) JooqInfix.getDao(clazz);
    }

    public <T> Future<List<T>> fetchAsync(final String column, final Object value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(DSL.field(column), Arrays.asList(value));
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchAndAsync(final JsonObject andFilters) {
        final Condition condition = transform(andFilters, Operator.AND);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchOrAsync(final JsonObject orFilters) {
        final Condition condition = transform(orFilters, Operator.OR);
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(condition);
        return Async.toFuture(future);
    }

    public <T> Future<List<T>> fetchInAsync(final String column, final Object... value) {
        final CompletableFuture<List<T>> future =
                this.vertxDAO.fetchAsync(DSL.field(column), Arrays.asList(value));
        return Async.toFuture(future);
    }

    public <T> Future<T> fetchOneAsync(final String column, final Object value) {
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(DSL.field(column), value);
        return Async.toFuture(future);
    }

    public <T> Future<T> updateOneAsync(T updated) {
        final CompletableFuture<T> future =
                this.vertxDAO.updateAsync(updated);
        return Async.toFuture(future.completedFuture(updated));
    }

    private Condition transform(final JsonObject filters, final Operator operator) {
        Condition condition = null;
        for (final String field : filters.fieldNames()) {
            final String key = getKey(field);
            final Object value = filters.getValue(field);
            // Function
            final BiFunction<String, Object, Condition> fun = OPS.get(key);
            final Condition item = fun.apply(field, value);
            if (null == condition) {
                condition = item;
            } else {
                condition = DSL.condition(operator, condition, item);
            }
        }
        return condition;
    }

    private String getKey(final String field) {
        if (!field.contains(",")) {
            return "=";
        } else {
            final String opStr = field.split(",")[Values.ONE];
            return StringUtil.isNil(opStr) ? "=" : opStr;
        }
    }

    private static ConcurrentMap<String, BiFunction<String, Object, Condition>> OPS =
            new ConcurrentHashMap<String, BiFunction<String, Object, Condition>>() {
                {
                    put("<", (field, value) -> DSL.field(field).lt(value));
                    put(">", (field, value) -> DSL.field(field).gt(value));
                    put("<=", (field, value) -> DSL.field(field).le(value));
                    put(">=", (field, value) -> DSL.field(field).ge(value));
                    put("=", (field, value) -> DSL.field(field).eq(value));
                    put("<>", (field, value) -> DSL.field(field).ne(value));
                    put("!n", (field, value) -> DSL.field(field).isNotNull());
                    put("n", (field, value) -> DSL.field(field).isNull());
                    put("t", (field, value) -> DSL.field(field).isTrue());
                    put("f", (field, value) -> DSL.field(field).isFalse());
                    put("i", (field, value) -> DSL.field(field).in(value));
                    put("!i", (field, value) -> DSL.field(field).notIn(value));
                    put("s", (field, value) -> DSL.field(field).startsWith(value));
                    put("e", (field, value) -> DSL.field(field).endsWith(value));
                    put("c", (field, value) -> DSL.field(field).contains(value));
                }
            };
}
