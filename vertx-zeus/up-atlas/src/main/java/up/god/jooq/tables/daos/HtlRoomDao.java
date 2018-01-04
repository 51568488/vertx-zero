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

import up.god.jooq.tables.HtlRoom;
import up.god.jooq.tables.records.HtlRoomRecord;


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
public class HtlRoomDao extends DAOImpl<HtlRoomRecord, up.god.jooq.tables.pojos.HtlRoom, Long> implements VertxDAO<up.god.jooq.tables.records.HtlRoomRecord,up.god.jooq.tables.pojos.HtlRoom,java.lang.Long> {

    /**
     * Create a new HtlRoomDao without any configuration
     */
    public HtlRoomDao() {
        super(HtlRoom.HTL_ROOM, up.god.jooq.tables.pojos.HtlRoom.class);
    }

    /**
     * Create a new HtlRoomDao with an attached configuration
     */
    public HtlRoomDao(Configuration configuration) {
        super(HtlRoom.HTL_ROOM, up.god.jooq.tables.pojos.HtlRoom.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(up.god.jooq.tables.pojos.HtlRoom object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByPkId(Long... values) {
        return fetch(HtlRoom.HTL_ROOM.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public up.god.jooq.tables.pojos.HtlRoom fetchOneByPkId(Long value) {
        return fetchOne(HtlRoom.HTL_ROOM.PK_ID, value);
    }

    /**
     * Fetch records that have <code>T_ROOMATTRS IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByTRoomattrs(String... values) {
        return fetch(HtlRoom.HTL_ROOM.T_ROOMATTRS, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchBySCode(String... values) {
        return fetch(HtlRoom.HTL_ROOM.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_ROOM_NUM IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchBySRoomNum(String... values) {
        return fetch(HtlRoom.HTL_ROOM.S_ROOM_NUM, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByJConfig(String... values) {
        return fetch(HtlRoom.HTL_ROOM.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>J_FEATURES IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByJFeatures(String... values) {
        return fetch(HtlRoom.HTL_ROOM.J_FEATURES, values);
    }

    /**
     * Fetch records that have <code>R_FLOOR_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByRFloorId(Long... values) {
        return fetch(HtlRoom.HTL_ROOM.R_FLOOR_ID, values);
    }

    /**
     * Fetch records that have <code>R_HOTEL_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByRHotelId(Long... values) {
        return fetch(HtlRoom.HTL_ROOM.R_HOTEL_ID, values);
    }

    /**
     * Fetch records that have <code>R_ROOM_TYPE_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByRRoomTypeId(Long... values) {
        return fetch(HtlRoom.HTL_ROOM.R_ROOM_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>R_TENT_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByRTentId(Long... values) {
        return fetch(HtlRoom.HTL_ROOM.R_TENT_ID, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByIsActive(Boolean... values) {
        return fetch(HtlRoom.HTL_ROOM.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZSigma(String... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZLanguage(String... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZCreateBy(String... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZUpdateBy(String... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRoom> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(HtlRoom.HTL_ROOM.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByPkIdAsync(List<Long> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<up.god.jooq.tables.pojos.HtlRoom> fetchOneByPkIdAsync(Long value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>T_ROOMATTRS IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByTRoomattrsAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.T_ROOMATTRS,values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.S_CODE,values);
    }

    /**
     * Fetch records that have <code>S_ROOM_NUM IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchBySRoomNumAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.S_ROOM_NUM,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>J_FEATURES IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByJFeaturesAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.J_FEATURES,values);
    }

    /**
     * Fetch records that have <code>R_FLOOR_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByRFloorIdAsync(List<Long> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.R_FLOOR_ID,values);
    }

    /**
     * Fetch records that have <code>R_HOTEL_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByRHotelIdAsync(List<Long> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.R_HOTEL_ID,values);
    }

    /**
     * Fetch records that have <code>R_ROOM_TYPE_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByRRoomTypeIdAsync(List<Long> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.R_ROOM_TYPE_ID,values);
    }

    /**
     * Fetch records that have <code>R_TENT_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByRTentIdAsync(List<Long> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.R_TENT_ID,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRoom>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(HtlRoom.HTL_ROOM.Z_UPDATE_TIME,values);
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
