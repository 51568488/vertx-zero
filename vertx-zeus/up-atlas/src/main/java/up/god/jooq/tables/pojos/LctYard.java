/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.ILctYard;


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
public class LctYard implements ILctYard {

    private static final long serialVersionUID = 1646402812;

    private Long          pkId;
    private String        sName;
    private String        sCode;
    private String        jConfig;
    private Integer       iOrder;
    private Long          rLocationId;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public LctYard() {}

    public LctYard(LctYard value) {
        this.pkId = value.pkId;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.jConfig = value.jConfig;
        this.iOrder = value.iOrder;
        this.rLocationId = value.rLocationId;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public LctYard(
        Long          pkId,
        String        sName,
        String        sCode,
        String        jConfig,
        Integer       iOrder,
        Long          rLocationId,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.sName = sName;
        this.sCode = sCode;
        this.jConfig = jConfig;
        this.iOrder = iOrder;
        this.rLocationId = rLocationId;
        this.isActive = isActive;
        this.zSigma = zSigma;
        this.zLanguage = zLanguage;
        this.zCreateBy = zCreateBy;
        this.zCreateTime = zCreateTime;
        this.zUpdateBy = zUpdateBy;
        this.zUpdateTime = zUpdateTime;
    }

    @Override
    public Long getPkId() {
        return this.pkId;
    }

    @Override
    public LctYard setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public LctYard setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public LctYard setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public LctYard setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Integer getIOrder() {
        return this.iOrder;
    }

    @Override
    public LctYard setIOrder(Integer iOrder) {
        this.iOrder = iOrder;
        return this;
    }

    @Override
    public Long getRLocationId() {
        return this.rLocationId;
    }

    @Override
    public LctYard setRLocationId(Long rLocationId) {
        this.rLocationId = rLocationId;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public LctYard setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public LctYard setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public LctYard setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public LctYard setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public LctYard setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public LctYard setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public LctYard setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LctYard (");

        sb.append(pkId);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(iOrder);
        sb.append(", ").append(rLocationId);
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
    public void from(ILctYard from) {
        setPkId(from.getPkId());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setJConfig(from.getJConfig());
        setIOrder(from.getIOrder());
        setRLocationId(from.getRLocationId());
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
    public <E extends ILctYard> E into(E into) {
        into.from(this);
        return into;
    }

    public LctYard(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
