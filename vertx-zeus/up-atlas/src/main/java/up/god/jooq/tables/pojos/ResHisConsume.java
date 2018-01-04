/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IResHisConsume;


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
public class ResHisConsume implements IResHisConsume {

    private static final long serialVersionUID = -1179349649;

    private String        pkId;
    private BigDecimal    dAmount;
    private LocalDateTime dConsumeTime;
    private String        sCode;
    private String        sSerial;
    private String        jConfig;
    private String        rCardId;
    private Boolean       isFinished;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public ResHisConsume() {}

    public ResHisConsume(ResHisConsume value) {
        this.pkId = value.pkId;
        this.dAmount = value.dAmount;
        this.dConsumeTime = value.dConsumeTime;
        this.sCode = value.sCode;
        this.sSerial = value.sSerial;
        this.jConfig = value.jConfig;
        this.rCardId = value.rCardId;
        this.isFinished = value.isFinished;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public ResHisConsume(
        String        pkId,
        BigDecimal    dAmount,
        LocalDateTime dConsumeTime,
        String        sCode,
        String        sSerial,
        String        jConfig,
        String        rCardId,
        Boolean       isFinished,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.dAmount = dAmount;
        this.dConsumeTime = dConsumeTime;
        this.sCode = sCode;
        this.sSerial = sSerial;
        this.jConfig = jConfig;
        this.rCardId = rCardId;
        this.isFinished = isFinished;
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
    public ResHisConsume setPkId(String pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public BigDecimal getDAmount() {
        return this.dAmount;
    }

    @Override
    public ResHisConsume setDAmount(BigDecimal dAmount) {
        this.dAmount = dAmount;
        return this;
    }

    @Override
    public LocalDateTime getDConsumeTime() {
        return this.dConsumeTime;
    }

    @Override
    public ResHisConsume setDConsumeTime(LocalDateTime dConsumeTime) {
        this.dConsumeTime = dConsumeTime;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public ResHisConsume setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSSerial() {
        return this.sSerial;
    }

    @Override
    public ResHisConsume setSSerial(String sSerial) {
        this.sSerial = sSerial;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public ResHisConsume setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public String getRCardId() {
        return this.rCardId;
    }

    @Override
    public ResHisConsume setRCardId(String rCardId) {
        this.rCardId = rCardId;
        return this;
    }

    @Override
    public Boolean getIsFinished() {
        return this.isFinished;
    }

    @Override
    public ResHisConsume setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public ResHisConsume setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public ResHisConsume setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public ResHisConsume setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public ResHisConsume setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public ResHisConsume setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public ResHisConsume setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public ResHisConsume setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResHisConsume (");

        sb.append(pkId);
        sb.append(", ").append(dAmount);
        sb.append(", ").append(dConsumeTime);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sSerial);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rCardId);
        sb.append(", ").append(isFinished);
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
    public void from(IResHisConsume from) {
        setPkId(from.getPkId());
        setDAmount(from.getDAmount());
        setDConsumeTime(from.getDConsumeTime());
        setSCode(from.getSCode());
        setSSerial(from.getSSerial());
        setJConfig(from.getJConfig());
        setRCardId(from.getRCardId());
        setIsFinished(from.getIsFinished());
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
    public <E extends IResHisConsume> E into(E into) {
        into.from(this);
        return into;
    }

    public ResHisConsume(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
