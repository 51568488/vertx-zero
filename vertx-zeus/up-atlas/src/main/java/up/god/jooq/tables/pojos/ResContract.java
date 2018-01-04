/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IResContract;


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
public class ResContract implements IResContract {

    private static final long serialVersionUID = 1459575443;

    private Long          pkId;
    private BigDecimal    dRunupLimit;
    private String        sName;
    private String        sCode;
    private String        sOptorMobile;
    private String        sOptorName;
    private String        sPhone;
    private String        jConfig;
    private Long          rltContractItem;
    private Long          rltContractType;
    private Long          rCompanyId;
    private String        rOperatorId;
    private Boolean       isRunup;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public ResContract() {}

    public ResContract(ResContract value) {
        this.pkId = value.pkId;
        this.dRunupLimit = value.dRunupLimit;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sOptorMobile = value.sOptorMobile;
        this.sOptorName = value.sOptorName;
        this.sPhone = value.sPhone;
        this.jConfig = value.jConfig;
        this.rltContractItem = value.rltContractItem;
        this.rltContractType = value.rltContractType;
        this.rCompanyId = value.rCompanyId;
        this.rOperatorId = value.rOperatorId;
        this.isRunup = value.isRunup;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public ResContract(
        Long          pkId,
        BigDecimal    dRunupLimit,
        String        sName,
        String        sCode,
        String        sOptorMobile,
        String        sOptorName,
        String        sPhone,
        String        jConfig,
        Long          rltContractItem,
        Long          rltContractType,
        Long          rCompanyId,
        String        rOperatorId,
        Boolean       isRunup,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.dRunupLimit = dRunupLimit;
        this.sName = sName;
        this.sCode = sCode;
        this.sOptorMobile = sOptorMobile;
        this.sOptorName = sOptorName;
        this.sPhone = sPhone;
        this.jConfig = jConfig;
        this.rltContractItem = rltContractItem;
        this.rltContractType = rltContractType;
        this.rCompanyId = rCompanyId;
        this.rOperatorId = rOperatorId;
        this.isRunup = isRunup;
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
    public ResContract setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public BigDecimal getDRunupLimit() {
        return this.dRunupLimit;
    }

    @Override
    public ResContract setDRunupLimit(BigDecimal dRunupLimit) {
        this.dRunupLimit = dRunupLimit;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public ResContract setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public ResContract setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSOptorMobile() {
        return this.sOptorMobile;
    }

    @Override
    public ResContract setSOptorMobile(String sOptorMobile) {
        this.sOptorMobile = sOptorMobile;
        return this;
    }

    @Override
    public String getSOptorName() {
        return this.sOptorName;
    }

    @Override
    public ResContract setSOptorName(String sOptorName) {
        this.sOptorName = sOptorName;
        return this;
    }

    @Override
    public String getSPhone() {
        return this.sPhone;
    }

    @Override
    public ResContract setSPhone(String sPhone) {
        this.sPhone = sPhone;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public ResContract setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Long getRltContractItem() {
        return this.rltContractItem;
    }

    @Override
    public ResContract setRltContractItem(Long rltContractItem) {
        this.rltContractItem = rltContractItem;
        return this;
    }

    @Override
    public Long getRltContractType() {
        return this.rltContractType;
    }

    @Override
    public ResContract setRltContractType(Long rltContractType) {
        this.rltContractType = rltContractType;
        return this;
    }

    @Override
    public Long getRCompanyId() {
        return this.rCompanyId;
    }

    @Override
    public ResContract setRCompanyId(Long rCompanyId) {
        this.rCompanyId = rCompanyId;
        return this;
    }

    @Override
    public String getROperatorId() {
        return this.rOperatorId;
    }

    @Override
    public ResContract setROperatorId(String rOperatorId) {
        this.rOperatorId = rOperatorId;
        return this;
    }

    @Override
    public Boolean getIsRunup() {
        return this.isRunup;
    }

    @Override
    public ResContract setIsRunup(Boolean isRunup) {
        this.isRunup = isRunup;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public ResContract setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public ResContract setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public ResContract setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public ResContract setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public ResContract setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public ResContract setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public ResContract setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResContract (");

        sb.append(pkId);
        sb.append(", ").append(dRunupLimit);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sOptorMobile);
        sb.append(", ").append(sOptorName);
        sb.append(", ").append(sPhone);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rltContractItem);
        sb.append(", ").append(rltContractType);
        sb.append(", ").append(rCompanyId);
        sb.append(", ").append(rOperatorId);
        sb.append(", ").append(isRunup);
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
    public void from(IResContract from) {
        setPkId(from.getPkId());
        setDRunupLimit(from.getDRunupLimit());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSOptorMobile(from.getSOptorMobile());
        setSOptorName(from.getSOptorName());
        setSPhone(from.getSPhone());
        setJConfig(from.getJConfig());
        setRltContractItem(from.getRltContractItem());
        setRltContractType(from.getRltContractType());
        setRCompanyId(from.getRCompanyId());
        setROperatorId(from.getROperatorId());
        setIsRunup(from.getIsRunup());
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
    public <E extends IResContract> E into(E into) {
        into.from(this);
        return into;
    }

    public ResContract(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
