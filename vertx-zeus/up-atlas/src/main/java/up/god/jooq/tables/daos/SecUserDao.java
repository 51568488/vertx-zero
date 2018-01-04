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

import up.god.jooq.tables.SecUser;
import up.god.jooq.tables.records.SecUserRecord;


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
public class SecUserDao extends DAOImpl<SecUserRecord, up.god.jooq.tables.pojos.SecUser, String> implements VertxDAO<up.god.jooq.tables.records.SecUserRecord,up.god.jooq.tables.pojos.SecUser,java.lang.String> {

    /**
     * Create a new SecUserDao without any configuration
     */
    public SecUserDao() {
        super(SecUser.SEC_USER, up.god.jooq.tables.pojos.SecUser.class);
    }

    /**
     * Create a new SecUserDao with an attached configuration
     */
    public SecUserDao(Configuration configuration) {
        super(SecUser.SEC_USER, up.god.jooq.tables.pojos.SecUser.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(up.god.jooq.tables.pojos.SecUser object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByPkId(String... values) {
        return fetch(SecUser.SEC_USER.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public up.god.jooq.tables.pojos.SecUser fetchOneByPkId(String value) {
        return fetchOne(SecUser.SEC_USER.PK_ID, value);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySCode(String... values) {
        return fetch(SecUser.SEC_USER.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_ALIPAY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySAlipay(String... values) {
        return fetch(SecUser.SEC_USER.S_ALIPAY, values);
    }

    /**
     * Fetch records that have <code>S_EMAIL IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySEmail(String... values) {
        return fetch(SecUser.SEC_USER.S_EMAIL, values);
    }

    /**
     * Fetch records that have <code>S_MOBILE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySMobile(String... values) {
        return fetch(SecUser.SEC_USER.S_MOBILE, values);
    }

    /**
     * Fetch records that have <code>S_PASSWORD IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySPassword(String... values) {
        return fetch(SecUser.SEC_USER.S_PASSWORD, values);
    }

    /**
     * Fetch records that have <code>S_QQ IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySQq(String... values) {
        return fetch(SecUser.SEC_USER.S_QQ, values);
    }

    /**
     * Fetch records that have <code>S_SECRET IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySSecret(String... values) {
        return fetch(SecUser.SEC_USER.S_SECRET, values);
    }

    /**
     * Fetch records that have <code>S_TAOBAO IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySTaobao(String... values) {
        return fetch(SecUser.SEC_USER.S_TAOBAO, values);
    }

    /**
     * Fetch records that have <code>S_USERNAME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySUsername(String... values) {
        return fetch(SecUser.SEC_USER.S_USERNAME, values);
    }

    /**
     * Fetch records that have <code>S_WECHAT IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySWechat(String... values) {
        return fetch(SecUser.SEC_USER.S_WECHAT, values);
    }

    /**
     * Fetch records that have <code>S_WEIBO IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchBySWeibo(String... values) {
        return fetch(SecUser.SEC_USER.S_WEIBO, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByJConfig(String... values) {
        return fetch(SecUser.SEC_USER.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByIsActive(Boolean... values) {
        return fetch(SecUser.SEC_USER.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZSigma(String... values) {
        return fetch(SecUser.SEC_USER.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZLanguage(String... values) {
        return fetch(SecUser.SEC_USER.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZCreateBy(String... values) {
        return fetch(SecUser.SEC_USER.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(SecUser.SEC_USER.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZUpdateBy(String... values) {
        return fetch(SecUser.SEC_USER.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.SecUser> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(SecUser.SEC_USER.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByPkIdAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<up.god.jooq.tables.pojos.SecUser> fetchOneByPkIdAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_CODE,values);
    }

    /**
     * Fetch records that have <code>S_ALIPAY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySAlipayAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_ALIPAY,values);
    }

    /**
     * Fetch records that have <code>S_EMAIL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySEmailAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_EMAIL,values);
    }

    /**
     * Fetch records that have <code>S_MOBILE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySMobileAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_MOBILE,values);
    }

    /**
     * Fetch records that have <code>S_PASSWORD IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySPasswordAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_PASSWORD,values);
    }

    /**
     * Fetch records that have <code>S_QQ IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySQqAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_QQ,values);
    }

    /**
     * Fetch records that have <code>S_SECRET IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySSecretAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_SECRET,values);
    }

    /**
     * Fetch records that have <code>S_TAOBAO IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySTaobaoAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_TAOBAO,values);
    }

    /**
     * Fetch records that have <code>S_USERNAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySUsernameAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_USERNAME,values);
    }

    /**
     * Fetch records that have <code>S_WECHAT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySWechatAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_WECHAT,values);
    }

    /**
     * Fetch records that have <code>S_WEIBO IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchBySWeiboAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.S_WEIBO,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(SecUser.SEC_USER.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SecUser.SEC_USER.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(SecUser.SEC_USER.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.SecUser>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(SecUser.SEC_USER.Z_UPDATE_TIME,values);
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
