package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.Future;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.container.TypeHod;
import org.jooq.impl.DSL;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
class UxJooq {

    private static final Annal LOGGER = Annal.get(UxJooq.class);

    /**
     * Get Dao
     *
     * @param <D>
     * @return
     */
    private static <D extends VertxDAO> VertxDAO get() {
        final Class<D> clazz = (Class<D>) new TypeHod<D>() {
        }.getType();
        LOGGER.info("[ ZERO ] Typed scanned for {0}", clazz.getName());
        return JooqInfix.getDao(clazz);
    }

    static <T, D extends VertxDAO> Future<List<T>> fetchByAsync(final String column, final String value) {
        final VertxDAO dao = get();
        final CompletableFuture<List<T>> future = get().fetchAsync(DSL.field(column), Arrays.asList(value));
        return Async.toFuture(future);
    }
}
