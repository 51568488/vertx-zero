/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IPsiRepository;


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
public class PsiRepository implements IPsiRepository {

    private static final long serialVersionUID = -803863414;

    private Long          pkId;
    private LocalDateTime dExpiredTime;
    private LocalDateTime dProduceTime;
    private String        tComment;
    private Long          vMerchandiseId;
    private String        sName;
    private String        sCode;
    private String        sWarehouse;
    private String        jConfig;
    private Integer       iQtyBroken;
    private Integer       iQtyLeft;
    private Integer       iQtyLimit;
    private Integer       iQtyTaken;
    private Long          rUbicationId;
    private Boolean       isReject;
    private Boolean       isScrap;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public PsiRepository() {}

    public PsiRepository(PsiRepository value) {
        this.pkId = value.pkId;
        this.dExpiredTime = value.dExpiredTime;
        this.dProduceTime = value.dProduceTime;
        this.tComment = value.tComment;
        this.vMerchandiseId = value.vMerchandiseId;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.sWarehouse = value.sWarehouse;
        this.jConfig = value.jConfig;
        this.iQtyBroken = value.iQtyBroken;
        this.iQtyLeft = value.iQtyLeft;
        this.iQtyLimit = value.iQtyLimit;
        this.iQtyTaken = value.iQtyTaken;
        this.rUbicationId = value.rUbicationId;
        this.isReject = value.isReject;
        this.isScrap = value.isScrap;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public PsiRepository(
        Long          pkId,
        LocalDateTime dExpiredTime,
        LocalDateTime dProduceTime,
        String        tComment,
        Long          vMerchandiseId,
        String        sName,
        String        sCode,
        String        sWarehouse,
        String        jConfig,
        Integer       iQtyBroken,
        Integer       iQtyLeft,
        Integer       iQtyLimit,
        Integer       iQtyTaken,
        Long          rUbicationId,
        Boolean       isReject,
        Boolean       isScrap,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.dExpiredTime = dExpiredTime;
        this.dProduceTime = dProduceTime;
        this.tComment = tComment;
        this.vMerchandiseId = vMerchandiseId;
        this.sName = sName;
        this.sCode = sCode;
        this.sWarehouse = sWarehouse;
        this.jConfig = jConfig;
        this.iQtyBroken = iQtyBroken;
        this.iQtyLeft = iQtyLeft;
        this.iQtyLimit = iQtyLimit;
        this.iQtyTaken = iQtyTaken;
        this.rUbicationId = rUbicationId;
        this.isReject = isReject;
        this.isScrap = isScrap;
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
    public PsiRepository setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public LocalDateTime getDExpiredTime() {
        return this.dExpiredTime;
    }

    @Override
    public PsiRepository setDExpiredTime(LocalDateTime dExpiredTime) {
        this.dExpiredTime = dExpiredTime;
        return this;
    }

    @Override
    public LocalDateTime getDProduceTime() {
        return this.dProduceTime;
    }

    @Override
    public PsiRepository setDProduceTime(LocalDateTime dProduceTime) {
        this.dProduceTime = dProduceTime;
        return this;
    }

    @Override
    public String getTComment() {
        return this.tComment;
    }

    @Override
    public PsiRepository setTComment(String tComment) {
        this.tComment = tComment;
        return this;
    }

    @Override
    public Long getVMerchandiseId() {
        return this.vMerchandiseId;
    }

    @Override
    public PsiRepository setVMerchandiseId(Long vMerchandiseId) {
        this.vMerchandiseId = vMerchandiseId;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public PsiRepository setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public PsiRepository setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSWarehouse() {
        return this.sWarehouse;
    }

    @Override
    public PsiRepository setSWarehouse(String sWarehouse) {
        this.sWarehouse = sWarehouse;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public PsiRepository setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Integer getIQtyBroken() {
        return this.iQtyBroken;
    }

    @Override
    public PsiRepository setIQtyBroken(Integer iQtyBroken) {
        this.iQtyBroken = iQtyBroken;
        return this;
    }

    @Override
    public Integer getIQtyLeft() {
        return this.iQtyLeft;
    }

    @Override
    public PsiRepository setIQtyLeft(Integer iQtyLeft) {
        this.iQtyLeft = iQtyLeft;
        return this;
    }

    @Override
    public Integer getIQtyLimit() {
        return this.iQtyLimit;
    }

    @Override
    public PsiRepository setIQtyLimit(Integer iQtyLimit) {
        this.iQtyLimit = iQtyLimit;
        return this;
    }

    @Override
    public Integer getIQtyTaken() {
        return this.iQtyTaken;
    }

    @Override
    public PsiRepository setIQtyTaken(Integer iQtyTaken) {
        this.iQtyTaken = iQtyTaken;
        return this;
    }

    @Override
    public Long getRUbicationId() {
        return this.rUbicationId;
    }

    @Override
    public PsiRepository setRUbicationId(Long rUbicationId) {
        this.rUbicationId = rUbicationId;
        return this;
    }

    @Override
    public Boolean getIsReject() {
        return this.isReject;
    }

    @Override
    public PsiRepository setIsReject(Boolean isReject) {
        this.isReject = isReject;
        return this;
    }

    @Override
    public Boolean getIsScrap() {
        return this.isScrap;
    }

    @Override
    public PsiRepository setIsScrap(Boolean isScrap) {
        this.isScrap = isScrap;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public PsiRepository setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public PsiRepository setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public PsiRepository setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public PsiRepository setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public PsiRepository setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public PsiRepository setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public PsiRepository setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PsiRepository (");

        sb.append(pkId);
        sb.append(", ").append(dExpiredTime);
        sb.append(", ").append(dProduceTime);
        sb.append(", ").append(tComment);
        sb.append(", ").append(vMerchandiseId);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sWarehouse);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(iQtyBroken);
        sb.append(", ").append(iQtyLeft);
        sb.append(", ").append(iQtyLimit);
        sb.append(", ").append(iQtyTaken);
        sb.append(", ").append(rUbicationId);
        sb.append(", ").append(isReject);
        sb.append(", ").append(isScrap);
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
    public void from(IPsiRepository from) {
        setPkId(from.getPkId());
        setDExpiredTime(from.getDExpiredTime());
        setDProduceTime(from.getDProduceTime());
        setTComment(from.getTComment());
        setVMerchandiseId(from.getVMerchandiseId());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setSWarehouse(from.getSWarehouse());
        setJConfig(from.getJConfig());
        setIQtyBroken(from.getIQtyBroken());
        setIQtyLeft(from.getIQtyLeft());
        setIQtyLimit(from.getIQtyLimit());
        setIQtyTaken(from.getIQtyTaken());
        setRUbicationId(from.getRUbicationId());
        setIsReject(from.getIsReject());
        setIsScrap(from.getIsScrap());
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
    public <E extends IPsiRepository> E into(E into) {
        into.from(this);
        return into;
    }

    public PsiRepository(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
