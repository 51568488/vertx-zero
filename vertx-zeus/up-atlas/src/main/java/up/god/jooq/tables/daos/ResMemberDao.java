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

import up.god.jooq.tables.ResMember;
import up.god.jooq.tables.records.ResMemberRecord;


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
public class ResMemberDao extends DAOImpl<ResMemberRecord, up.god.jooq.tables.pojos.ResMember, String> implements VertxDAO<up.god.jooq.tables.records.ResMemberRecord,up.god.jooq.tables.pojos.ResMember,java.lang.String> {

    /**
     * Create a new ResMemberDao without any configuration
     */
    public ResMemberDao() {
        super(ResMember.RES_MEMBER, up.god.jooq.tables.pojos.ResMember.class);
    }

    /**
     * Create a new ResMemberDao with an attached configuration
     */
    public ResMemberDao(Configuration configuration) {
        super(ResMember.RES_MEMBER, up.god.jooq.tables.pojos.ResMember.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(up.god.jooq.tables.pojos.ResMember object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByPkId(String... values) {
        return fetch(ResMember.RES_MEMBER.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public up.god.jooq.tables.pojos.ResMember fetchOneByPkId(String value) {
        return fetchOne(ResMember.RES_MEMBER.PK_ID, value);
    }

    /**
     * Fetch records that have <code>D_BIRTHDAY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByDBirthday(LocalDateTime... values) {
        return fetch(ResMember.RES_MEMBER.D_BIRTHDAY, values);
    }

    /**
     * Fetch records that have <code>L_POINT IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByLPoint(Long... values) {
        return fetch(ResMember.RES_MEMBER.L_POINT, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchBySCode(String... values) {
        return fetch(ResMember.RES_MEMBER.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_NICKNAME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchBySNickname(String... values) {
        return fetch(ResMember.RES_MEMBER.S_NICKNAME, values);
    }

    /**
     * Fetch records that have <code>S_PHOTO IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchBySPhoto(String... values) {
        return fetch(ResMember.RES_MEMBER.S_PHOTO, values);
    }

    /**
     * Fetch records that have <code>S_REALNAME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchBySRealname(String... values) {
        return fetch(ResMember.RES_MEMBER.S_REALNAME, values);
    }

    /**
     * Fetch records that have <code>S_SERIAL IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchBySSerial(String... values) {
        return fetch(ResMember.RES_MEMBER.S_SERIAL, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByJConfig(String... values) {
        return fetch(ResMember.RES_MEMBER.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>RLT_MBER_LEVEL IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByRltMberLevel(Long... values) {
        return fetch(ResMember.RES_MEMBER.RLT_MBER_LEVEL, values);
    }

    /**
     * Fetch records that have <code>RLT_MBER_STATUS IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByRltMberStatus(Long... values) {
        return fetch(ResMember.RES_MEMBER.RLT_MBER_STATUS, values);
    }

    /**
     * Fetch records that have <code>R_USER_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByRUserId(String... values) {
        return fetch(ResMember.RES_MEMBER.R_USER_ID, values);
    }

    /**
     * Fetch records that have <code>IS_MALE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByIsMale(Boolean... values) {
        return fetch(ResMember.RES_MEMBER.IS_MALE, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByIsActive(Boolean... values) {
        return fetch(ResMember.RES_MEMBER.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZSigma(String... values) {
        return fetch(ResMember.RES_MEMBER.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZLanguage(String... values) {
        return fetch(ResMember.RES_MEMBER.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZCreateBy(String... values) {
        return fetch(ResMember.RES_MEMBER.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(ResMember.RES_MEMBER.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZUpdateBy(String... values) {
        return fetch(ResMember.RES_MEMBER.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.ResMember> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(ResMember.RES_MEMBER.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByPkIdAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<up.god.jooq.tables.pojos.ResMember> fetchOneByPkIdAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>D_BIRTHDAY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByDBirthdayAsync(List<LocalDateTime> values) {
        return fetchAsync(ResMember.RES_MEMBER.D_BIRTHDAY,values);
    }

    /**
     * Fetch records that have <code>L_POINT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByLPointAsync(List<Long> values) {
        return fetchAsync(ResMember.RES_MEMBER.L_POINT,values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.S_CODE,values);
    }

    /**
     * Fetch records that have <code>S_NICKNAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchBySNicknameAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.S_NICKNAME,values);
    }

    /**
     * Fetch records that have <code>S_PHOTO IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchBySPhotoAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.S_PHOTO,values);
    }

    /**
     * Fetch records that have <code>S_REALNAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchBySRealnameAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.S_REALNAME,values);
    }

    /**
     * Fetch records that have <code>S_SERIAL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchBySSerialAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.S_SERIAL,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>RLT_MBER_LEVEL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByRltMberLevelAsync(List<Long> values) {
        return fetchAsync(ResMember.RES_MEMBER.RLT_MBER_LEVEL,values);
    }

    /**
     * Fetch records that have <code>RLT_MBER_STATUS IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByRltMberStatusAsync(List<Long> values) {
        return fetchAsync(ResMember.RES_MEMBER.RLT_MBER_STATUS,values);
    }

    /**
     * Fetch records that have <code>R_USER_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByRUserIdAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.R_USER_ID,values);
    }

    /**
     * Fetch records that have <code>IS_MALE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByIsMaleAsync(List<Boolean> values) {
        return fetchAsync(ResMember.RES_MEMBER.IS_MALE,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(ResMember.RES_MEMBER.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.ResMember>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(ResMember.RES_MEMBER.Z_UPDATE_TIME,values);
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
