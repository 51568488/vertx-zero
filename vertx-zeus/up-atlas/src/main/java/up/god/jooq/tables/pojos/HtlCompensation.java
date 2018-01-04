/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IHtlCompensation;


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
public class HtlCompensation implements IHtlCompensation {

    private static final long serialVersionUID = 249104579;

    private Long          pkId;
    private BigDecimal    dPrice;
    private String        sName;
    private String        sCode;
    private String        sHelpCode;
    private String        sUnit;
    private String        jConfig;
    private Long          rHotelId;
    private String        rPaytermId;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public HtlCompensation() {}

    public HtlCompensation(HtlCompensation value) {
        this.pkId = value.pkId;
        this.dPrice = value.dPrice;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sHelpCode = value.sHelpCode;
        this.sUnit = value.sUnit;
        this.jConfig = value.jConfig;
        this.rHotelId = value.rHotelId;
        this.rPaytermId = value.rPaytermId;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public HtlCompensation(
        Long          pkId,
        BigDecimal    dPrice,
        String        sName,
        String        sCode,
        String        sHelpCode,
        String        sUnit,
        String        jConfig,
        Long          rHotelId,
        String        rPaytermId,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.dPrice = dPrice;
        this.sName = sName;
        this.sCode = sCode;
        this.sHelpCode = sHelpCode;
        this.sUnit = sUnit;
        this.jConfig = jConfig;
        this.rHotelId = rHotelId;
        this.rPaytermId = rPaytermId;
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
    public HtlCompensation setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public BigDecimal getDPrice() {
        return this.dPrice;
    }

    @Override
    public HtlCompensation setDPrice(BigDecimal dPrice) {
        this.dPrice = dPrice;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public HtlCompensation setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public HtlCompensation setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSHelpCode() {
        return this.sHelpCode;
    }

    @Override
    public HtlCompensation setSHelpCode(String sHelpCode) {
        this.sHelpCode = sHelpCode;
        return this;
    }

    @Override
    public String getSUnit() {
        return this.sUnit;
    }

    @Override
    public HtlCompensation setSUnit(String sUnit) {
        this.sUnit = sUnit;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public HtlCompensation setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Long getRHotelId() {
        return this.rHotelId;
    }

    @Override
    public HtlCompensation setRHotelId(Long rHotelId) {
        this.rHotelId = rHotelId;
        return this;
    }

    @Override
    public String getRPaytermId() {
        return this.rPaytermId;
    }

    @Override
    public HtlCompensation setRPaytermId(String rPaytermId) {
        this.rPaytermId = rPaytermId;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public HtlCompensation setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public HtlCompensation setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public HtlCompensation setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public HtlCompensation setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public HtlCompensation setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public HtlCompensation setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public HtlCompensation setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HtlCompensation (");

        sb.append(pkId);
        sb.append(", ").append(dPrice);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sHelpCode);
        sb.append(", ").append(sUnit);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rHotelId);
        sb.append(", ").append(rPaytermId);
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
    public void from(IHtlCompensation from) {
        setPkId(from.getPkId());
        setDPrice(from.getDPrice());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSHelpCode(from.getSHelpCode());
        setSUnit(from.getSUnit());
        setJConfig(from.getJConfig());
        setRHotelId(from.getRHotelId());
        setRPaytermId(from.getRPaytermId());
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
    public <E extends IHtlCompensation> E into(E into) {
        into.from(this);
        return into;
    }

    public HtlCompensation(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
