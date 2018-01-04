/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IOthUser;


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
public class OthUser implements IOthUser {

    private static final long serialVersionUID = -333288321;

    private String        pkId;
    private String        tRedirectUri;
    private String        sCode;
    private String        sClientSecret;
    private String        sGrantType;
    private String        sScope;
    private String        jConfig;
    private String        rClientId;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public OthUser() {}

    public OthUser(OthUser value) {
        this.pkId = value.pkId;
        this.tRedirectUri = value.tRedirectUri;
        this.sCode = value.sCode;
        this.sClientSecret = value.sClientSecret;
        this.sGrantType = value.sGrantType;
        this.sScope = value.sScope;
        this.jConfig = value.jConfig;
        this.rClientId = value.rClientId;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public OthUser(
        String        pkId,
        String        tRedirectUri,
        String        sCode,
        String        sClientSecret,
        String        sGrantType,
        String        sScope,
        String        jConfig,
        String        rClientId,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.tRedirectUri = tRedirectUri;
        this.sCode = sCode;
        this.sClientSecret = sClientSecret;
        this.sGrantType = sGrantType;
        this.sScope = sScope;
        this.jConfig = jConfig;
        this.rClientId = rClientId;
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
    public OthUser setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getTRedirectUri() {
        return this.tRedirectUri;
    }

    @Override
    public OthUser setTRedirectUri(String tRedirectUri) {
        this.tRedirectUri = tRedirectUri;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public OthUser setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSClientSecret() {
        return this.sClientSecret;
    }

    @Override
    public OthUser setSClientSecret(String sClientSecret) {
        this.sClientSecret = sClientSecret;
        return this;
    }

    @Override
    public String getSGrantType() {
        return this.sGrantType;
    }

    @Override
    public OthUser setSGrantType(String sGrantType) {
        this.sGrantType = sGrantType;
        return this;
    }

    @Override
    public String getSScope() {
        return this.sScope;
    }

    @Override
    public OthUser setSScope(String sScope) {
        this.sScope = sScope;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public OthUser setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public String getRClientId() {
        return this.rClientId;
    }

    @Override
    public OthUser setRClientId(String rClientId) {
        this.rClientId = rClientId;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public OthUser setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public OthUser setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public OthUser setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public OthUser setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public OthUser setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public OthUser setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public OthUser setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OthUser (");

        sb.append(pkId);
        sb.append(", ").append(tRedirectUri);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sClientSecret);
        sb.append(", ").append(sGrantType);
        sb.append(", ").append(sScope);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rClientId);
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
    public void from(IOthUser from) {
        setPkId(from.getPkId());
        setTRedirectUri(from.getTRedirectUri());
        setSCode(from.getSCode());
        setSClientSecret(from.getSClientSecret());
        setSGrantType(from.getSGrantType());
        setSScope(from.getSScope());
        setJConfig(from.getJConfig());
        setRClientId(from.getRClientId());
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
    public <E extends IOthUser> E into(E into) {
        into.from(this);
        return into;
    }

    public OthUser(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
