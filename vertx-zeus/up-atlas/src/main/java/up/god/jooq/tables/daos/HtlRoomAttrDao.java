/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.daos;


import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import up.god.jooq.tables.HtlRoomAttr;
import up.god.jooq.tables.records.HtlRoomAttrRecord;


import java.util.concurrent.CompletableFuture;
import io.github.jklingsporn.vertx.jooq.future.util.FutureTool;
/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HtlRoomAttrDao extends DAOImpl<HtlRoomAttrRecord, up.god.jooq.tables.pojos.HtlRoomAttr, Long> implements VertxDAO<up.god.jooq.tables.records.HtlRoomAttrRecord,up.god.jooq.tables.pojos.HtlRoomAttr,java.lang.Long> {

    /**
     * Create a new HtlRoomAttrDao without any configuration
     */
    public HtlRoomAttrDao() {
        super(HtlRoomAttr.HTL_ROOM_ATTR, up.god.jooq.tables.pojos.HtlRoomAttr.class);
    }

    /**
     * Create a new HtlRoomAttrDao with an attached configuration
     */
    public HtlRoomAttrDao(Configuration configuration) {
        super(HtlRoomAttr.HTL_ROOM_ATTR, up.god.jooq.tables.pojos.HtlRoomAttr.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(up.god.jooq.tables.pojos.HtlRoomAttr object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByPkId(Long... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public up.god.jooq.tables.pojos.HtlRoomAttr fetchOneByPkId(Long value) {
        return fetchOne(HtlRoomAttr.HTL_ROOM_ATTR.PK_ID, value);
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByTComment(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.T_COMMENT, values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchBySName(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.S_NAME, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchBySCode(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.S_CODE, values);
    }

    /**
     * Fetch records that have <code>J_COLOR IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByJColor(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.J_COLOR, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByJConfig(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>I_ORDER IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByIOrder(Integer... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.I_ORDER, values);
    }

    /**
     * Fetch records that have <code>R_HOTEL_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByRHotelId(Long... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.R_HOTEL_ID, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByIsActive(Boolean... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZSigma(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZLanguage(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZCreateBy(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZUpdateBy(String... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoomAttr> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(HtlRoomAttr.HTL_ROOM_ATTR.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByPkIdAsync(List<Long> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<up.god.jooq.tables.pojos.HtlRoomAttr> fetchOneByPkIdAsync(Long value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByTCommentAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.T_COMMENT,values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchBySNameAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.S_NAME,values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.S_CODE,values);
    }

    /**
     * Fetch records that have <code>J_COLOR IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByJColorAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.J_COLOR,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>I_ORDER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByIOrderAsync(List<Integer> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.I_ORDER,values);
    }

    /**
     * Fetch records that have <code>R_HOTEL_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByRHotelIdAsync(List<Long> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.R_HOTEL_ID,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoomAttr>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(HtlRoomAttr.HTL_ROOM_ATTR.Z_UPDATE_TIME,values);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
