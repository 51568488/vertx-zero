/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IHtlLost;


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
public class HtlLost implements IHtlLost {

    private static final long serialVersionUID = -1136284764;

    private Long          pkId;
    private String        tComment;
    private String        sCode;
    private String        sCall;
    private String        sContact;
    private String        sRoomNumber;
    private String        sStatus;
    private String        sThing;
    private String        jConfig;
    private Long          rHotelId;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public HtlLost() {}

    public HtlLost(HtlLost value) {
        this.pkId = value.pkId;
        this.tComment = value.tComment;
        this.sCode = value.sCode;
        this.sCall = value.sCall;
        this.sContact = value.sContact;
        this.sRoomNumber = value.sRoomNumber;
        this.sStatus = value.sStatus;
        this.sThing = value.sThing;
        this.jConfig = value.jConfig;
        this.rHotelId = value.rHotelId;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public HtlLost(
        Long          pkId,
        String        tComment,
        String        sCode,
        String        sCall,
        String        sContact,
        String        sRoomNumber,
        String        sStatus,
        String        sThing,
        String        jConfig,
        Long          rHotelId,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.tComment = tComment;
        this.sCode = sCode;
        this.sCall = sCall;
        this.sContact = sContact;
        this.sRoomNumber = sRoomNumber;
        this.sStatus = sStatus;
        this.sThing = sThing;
        this.jConfig = jConfig;
        this.rHotelId = rHotelId;
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
    public HtlLost setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public String getTComment() {
        return this.tComment;
    }

    @Override
    public HtlLost setTComment(String tComment) {
        this.tComment = tComment;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public HtlLost setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getSCall() {
        return this.sCall;
    }

    @Override
    public HtlLost setSCall(String sCall) {
        this.sCall = sCall;
        return this;
    }

    @Override
    public String getSContact() {
        return this.sContact;
    }

    @Override
    public HtlLost setSContact(String sContact) {
        this.sContact = sContact;
        return this;
    }

    @Override
    public String getSRoomNumber() {
        return this.sRoomNumber;
    }

    @Override
    public HtlLost setSRoomNumber(String sRoomNumber) {
        this.sRoomNumber = sRoomNumber;
        return this;
    }

    @Override
    public String getSStatus() {
        return this.sStatus;
    }

    @Override
    public HtlLost setSStatus(String sStatus) {
        this.sStatus = sStatus;
        return this;
    }

    @Override
    public String getSThing() {
        return this.sThing;
    }

    @Override
    public HtlLost setSThing(String sThing) {
        this.sThing = sThing;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public HtlLost setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Long getRHotelId() {
        return this.rHotelId;
    }

    @Override
    public HtlLost setRHotelId(Long rHotelId) {
        this.rHotelId = rHotelId;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public HtlLost setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public HtlLost setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public HtlLost setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public HtlLost setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public HtlLost setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public HtlLost setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public HtlLost setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HtlLost (");

        sb.append(pkId);
        sb.append(", ").append(tComment);
        sb.append(", ").append(sCode);
        sb.append(", ").append(sCall);
        sb.append(", ").append(sContact);
        sb.append(", ").append(sRoomNumber);
        sb.append(", ").append(sStatus);
        sb.append(", ").append(sThing);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rHotelId);
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
    public void from(IHtlLost from) {
        setPkId(from.getPkId());
        setTComment(from.getTComment());
        setSCode(from.getSCode());
        setSCall(from.getSCall());
        setSContact(from.getSContact());
        setSRoomNumber(from.getSRoomNumber());
        setSStatus(from.getSStatus());
        setSThing(from.getSThing());
        setJConfig(from.getJConfig());
        setRHotelId(from.getRHotelId());
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
    public <E extends IHtlLost> E into(E into) {
        into.from(this);
        return into;
    }

    public HtlLost(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
