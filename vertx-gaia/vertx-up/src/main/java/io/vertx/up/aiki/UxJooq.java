package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.log.Annal;
import org.jooq.impl.DSL;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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


    public <T> Future<T> fetchOneAsync(final String column, final Object value) {
        final CompletableFuture<T> future =
                this.vertxDAO.fetchOneAsync(DSL.field(column), value);
        return Async.toFuture(future);
    }

    public <T> Future<T> updateOneAsync(T updated) {
        final CompletableFuture<T> future =
                this.vertxDAO.updateAsync(updated);
        return Async.toFuture(future);
    }
}
