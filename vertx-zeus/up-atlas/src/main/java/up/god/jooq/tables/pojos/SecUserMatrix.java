/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.ISecUserMatrix;


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
public class SecUserMatrix implements ISecUserMatrix {

    private static final long serialVersionUID = -735243730;

    private String        pkId;
    private Long          lDuration;
    private String        tProjection;
    private String        tQuery;
    private String        sCode;
    private String        sActionCode;
    private String        jConfig;
    private String        rUserId;
    private String        rResId;
    private Boolean       isApproval;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public SecUserMatrix() {}

    public SecUserMatrix(SecUserMatrix value) {
        this.pkId = value.pkId;
        this.lDuration = value.lDuration;
        this.tProjection = value.tProjection;
        this.tQuery = value.tQuery;
        this.sCode = value.sCode;
        this.sActionCode = value.sActionCode;
        this.jConfig = value.jConfig;
        this.rUserId = value.rUserId;
        this.rResId = value.rResId;
        this.isApproval = value.isApproval;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public SecUserMatrix(
        String        pkId,
        Long          lDuration,
        String        tProjection,
        String        tQuery,
        String        sCode,
        String        sActionCode,
        String        jConfig,
        String        rUserId,
        String        rResId,
        Boolean       isApproval,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.lDuration = lDuration;
        this.tProjection = tProjection;
        this.tQuery = tQuery;
        this.sCode = sCode;
        this.sActionCode = sActionCode;
        this.jConfig = jConfig;
        this.rUserId = rUserId;
        this.rResId = rResId;
        this.isApproval = isApproval;
        this.isActive = isActive;
        this.zSigma = zSigma;
        this.zLanguage = zLanguage;
        this.zCreateBy = zCreateBy;
        this.zCreateTime = zCreateTime;
        this.zUpdateBy = zUpdateBy;
        this.zUpdateTime = zUpdateTime;
    }

    @Override
    public String getPkId() {
        return this.pkId;
    }

    @Override
    public SecUserMatrix setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public Long getLDuration() {
        return this.lDuration;
    }

    @Override
    public SecUserMatrix setLDuration(Long lDuration) {
        this.lDuration = lDuration;
        return this;
    }

    @Override
    public String getTProjection() {
        return this.tProjection;
    }

    @Override
    public SecUserMatrix setTProjection(String tProjection) {
        this.tProjection = tProjection;
        return this;
    }

    @Override
    public String getTQuery() {
        return this.tQuery;
    }

    @Override
    public SecUserMatrix setTQuery(String tQuery) {
        this.tQuery = tQuery;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public SecUserMatrix setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSActionCode() {
        return this.sActionCode;
    }

    @Override
    public SecUserMatrix setSActionCode(String sActionCode) {
        this.sActionCode = sActionCode;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public SecUserMatrix setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public String getRUserId() {
        return this.rUserId;
    }

    @Override
    public SecUserMatrix setRUserId(String rUserId) {
        this.rUserId = rUserId;
        return this;
    }

    @Override
    public String getRResId() {
        return this.rResId;
    }

    @Override
    public SecUserMatrix setRResId(String rResId) {
        this.rResId = rResId;
        return this;
    }

    @Override
    public Boolean getIsApproval() {
        return this.isApproval;
    }

    @Override
    public SecUserMatrix setIsApproval(Boolean isApproval) {
        this.isApproval = isApproval;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public SecUserMatrix setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public SecUserMatrix setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public SecUserMatrix setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public SecUserMatrix setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public SecUserMatrix setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public SecUserMatrix setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public SecUserMatrix setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SecUserMatrix (");

        sb.append(pkId);
        sb.append(", ").append(lDuration);
        sb.append(", ").append(tProjection);
        sb.append(", ").append(tQuery);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sActionCode);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rUserId);
        sb.append(", ").append(rResId);
        sb.append(", ").append(isApproval);
        sb.append(", ").append(isActive);
        sb.append(", ").append(zSigma);
        sb.append(", ").append(zLanguage);
        sb.append(", ").append(zCreateBy);
        sb.append(", ").append(zCreateTime);
        sb.append(", ").append(zUpdateBy);
        sb.append(", ").append(zUpdateTime);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISecUserMatrix from) {
        setPkId(from.getPkId());
        setLDuration(from.getLDuration());
        setTProjection(from.getTProjection());
        setTQuery(from.getTQuery());
        setSCode(from.getSCode());
        setSActionCode(from.getSActionCode());
        setJConfig(from.getJConfig());
        setRUserId(from.getRUserId());
        setRResId(from.getRResId());
        setIsApproval(from.getIsApproval());
        setIsActive(from.getIsActive());
        setZSigma(from.getZSigma());
        setZLanguage(from.getZLanguage());
        setZCreateBy(from.getZCreateBy());
        setZCreateTime(from.getZCreateTime());
        setZUpdateBy(from.getZUpdateBy());
        setZUpdateTime(from.getZUpdateTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISecUserMatrix> E into(E into) {
        into.from(this);
        return into;
    }

    public SecUserMatrix(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
