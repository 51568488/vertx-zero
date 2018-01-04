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

import up.god.jooq.tables.PsiOrdInquiry;
import up.god.jooq.tables.records.PsiOrdInquiryRecord;


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
public class PsiOrdInquiryDao extends DAOImpl<PsiOrdInquiryRecord, up.god.jooq.tables.pojos.PsiOrdInquiry, String> implements VertxDAO<up.god.jooq.tables.records.PsiOrdInquiryRecord,up.god.jooq.tables.pojos.PsiOrdInquiry,java.lang.String> {

    /**
     * Create a new PsiOrdInquiryDao without any configuration
     */
    public PsiOrdInquiryDao() {
        super(PsiOrdInquiry.PSI_ORD_INQUIRY, up.god.jooq.tables.pojos.PsiOrdInquiry.class);
    }

    /**
     * Create a new PsiOrdInquiryDao with an attached configuration
     */
    public PsiOrdInquiryDao(Configuration configuration) {
        super(PsiOrdInquiry.PSI_ORD_INQUIRY, up.god.jooq.tables.pojos.PsiOrdInquiry.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(up.god.jooq.tables.pojos.PsiOrdInquiry object) {
        return object.getPkId();
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByPkId(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.PK_ID, values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code>
     */
    public up.god.jooq.tables.pojos.PsiOrdInquiry fetchOneByPkId(String value) {
        return fetchOne(PsiOrdInquiry.PSI_ORD_INQUIRY.PK_ID, value);
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByTComment(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.T_COMMENT, values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchBySName(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.S_NAME, values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchBySCode(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.S_CODE, values);
    }

    /**
     * Fetch records that have <code>S_OPERATOR IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchBySOperator(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.S_OPERATOR, values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByJConfig(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.J_CONFIG, values);
    }

    /**
     * Fetch records that have <code>R_ORDER_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByROrderId(Long... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.R_ORDER_ID, values);
    }

    /**
     * Fetch records that have <code>R_UBICATION_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByRUbicationId(Long... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.R_UBICATION_ID, values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByIsActive(Boolean... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.IS_ACTIVE, values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZSigma(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_SIGMA, values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZLanguage(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZCreateBy(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_CREATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZCreateTime(LocalDateTime... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZUpdateBy(String... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_UPDATE_BY, values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchByZUpdateTime(LocalDateTime... values) {
        return fetch(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_UPDATE_TIME, values);
    }

    /**
     * Fetch records that have <code>PK_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByPkIdAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.PK_ID,values);
    }

    /**
     * Fetch a unique record that has <code>PK_ID = value</code> asynchronously
     */
    public CompletableFuture<up.god.jooq.tables.pojos.PsiOrdInquiry> fetchOneByPkIdAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByPkId(value)),vertx());
    }

    /**
     * Fetch records that have <code>T_COMMENT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByTCommentAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.T_COMMENT,values);
    }

    /**
     * Fetch records that have <code>S_NAME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchBySNameAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.S_NAME,values);
    }

    /**
     * Fetch records that have <code>S_CODE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchBySCodeAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.S_CODE,values);
    }

    /**
     * Fetch records that have <code>S_OPERATOR IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchBySOperatorAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.S_OPERATOR,values);
    }

    /**
     * Fetch records that have <code>J_CONFIG IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByJConfigAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.J_CONFIG,values);
    }

    /**
     * Fetch records that have <code>R_ORDER_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByROrderIdAsync(List<Long> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.R_ORDER_ID,values);
    }

    /**
     * Fetch records that have <code>R_UBICATION_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByRUbicationIdAsync(List<Long> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.R_UBICATION_ID,values);
    }

    /**
     * Fetch records that have <code>IS_ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByIsActiveAsync(List<Boolean> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.IS_ACTIVE,values);
    }

    /**
     * Fetch records that have <code>Z_SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZSigmaAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_SIGMA,values);
    }

    /**
     * Fetch records that have <code>Z_LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZLanguageAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZCreateByAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_CREATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_CREATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZCreateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_CREATE_TIME,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZUpdateByAsync(List<String> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_UPDATE_BY,values);
    }

    /**
     * Fetch records that have <code>Z_UPDATE_TIME IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.PsiOrdInquiry>> fetchByZUpdateTimeAsync(List<LocalDateTime> values) {
        return fetchAsync(PsiOrdInquiry.PSI_ORD_INQUIRY.Z_UPDATE_TIME,values);
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
