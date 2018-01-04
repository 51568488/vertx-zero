/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.pojos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import up.god.jooq.tables.interfaces.IHtlCodePrice;


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
public class HtlCodePrice implements IHtlCodePrice {

    private static final long serialVersionUID = -1960322600;

    private Long          pkId;
    private BigDecimal    dBkPrice;
    private BigDecimal    dPrice;
    private String        sName;
    private String        sCode;
    private String        jConfig;
    private Long          rltMarket;
    private Long          rltPricecat;
    private Long          rltSource;
    private Long          rHotelId;
    private Long          rRoomTypeId;
    private Boolean       isHasbrek;
    private Boolean       isActive;
    private String        zSigma;
    private String        zLanguage;
    private String        zCreateBy;
    private LocalDateTime zCreateTime;
    private String        zUpdateBy;
    private LocalDateTime zUpdateTime;

    public HtlCodePrice() {}

    public HtlCodePrice(HtlCodePrice value) {
        this.pkId = value.pkId;
        this.dBkPrice = value.dBkPrice;
        this.dPrice = value.dPrice;
        this.sName = value.sName;
        this.sCode = value.sCode;
        this.jConfig = value.jConfig;
        this.rltMarket = value.rltMarket;
        this.rltPricecat = value.rltPricecat;
        this.rltSource = value.rltSource;
        this.rHotelId = value.rHotelId;
        this.rRoomTypeId = value.rRoomTypeId;
        this.isHasbrek = value.isHasbrek;
        this.isActive = value.isActive;
        this.zSigma = value.zSigma;
        this.zLanguage = value.zLanguage;
        this.zCreateBy = value.zCreateBy;
        this.zCreateTime = value.zCreateTime;
        this.zUpdateBy = value.zUpdateBy;
        this.zUpdateTime = value.zUpdateTime;
    }

    public HtlCodePrice(
        Long          pkId,
        BigDecimal    dBkPrice,
        BigDecimal    dPrice,
        String        sName,
        String        sCode,
        String        jConfig,
        Long          rltMarket,
        Long          rltPricecat,
        Long          rltSource,
        Long          rHotelId,
        Long          rRoomTypeId,
        Boolean       isHasbrek,
        Boolean       isActive,
        String        zSigma,
        String        zLanguage,
        String        zCreateBy,
        LocalDateTime zCreateTime,
        String        zUpdateBy,
        LocalDateTime zUpdateTime
    ) {
        this.pkId = pkId;
        this.dBkPrice = dBkPrice;
        this.dPrice = dPrice;
        this.sName = sName;
        this.sCode = sCode;
        this.jConfig = jConfig;
        this.rltMarket = rltMarket;
        this.rltPricecat = rltPricecat;
        this.rltSource = rltSource;
        this.rHotelId = rHotelId;
        this.rRoomTypeId = rRoomTypeId;
        this.isHasbrek = isHasbrek;
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
    public HtlCodePrice setPkId(Long pkId) {
        this.pkId = pkId;
        return this;
    }

    @Override
    public BigDecimal getDBkPrice() {
        return this.dBkPrice;
    }

    @Override
    public HtlCodePrice setDBkPrice(BigDecimal dBkPrice) {
        this.dBkPrice = dBkPrice;
        return this;
    }

    @Override
    public BigDecimal getDPrice() {
        return this.dPrice;
    }

    @Override
    public HtlCodePrice setDPrice(BigDecimal dPrice) {
        this.dPrice = dPrice;
        return this;
    }

    @Override
    public String getSName() {
        return this.sName;
    }

    @Override
    public HtlCodePrice setSName(String sName) {
        this.sName = sName;
        return this;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public HtlCodePrice setSCode(String sCode) {
        this.sCode = sCode;
        return this;
    }

    @Override
    public String getJConfig() {
        return this.jConfig;
    }

    @Override
    public HtlCodePrice setJConfig(String jConfig) {
        this.jConfig = jConfig;
        return this;
    }

    @Override
    public Long getRltMarket() {
        return this.rltMarket;
    }

    @Override
    public HtlCodePrice setRltMarket(Long rltMarket) {
        this.rltMarket = rltMarket;
        return this;
    }

    @Override
    public Long getRltPricecat() {
        return this.rltPricecat;
    }

    @Override
    public HtlCodePrice setRltPricecat(Long rltPricecat) {
        this.rltPricecat = rltPricecat;
        return this;
    }

    @Override
    public Long getRltSource() {
        return this.rltSource;
    }

    @Override
    public HtlCodePrice setRltSource(Long rltSource) {
        this.rltSource = rltSource;
        return this;
    }

    @Override
    public Long getRHotelId() {
        return this.rHotelId;
    }

    @Override
    public HtlCodePrice setRHotelId(Long rHotelId) {
        this.rHotelId = rHotelId;
        return this;
    }

    @Override
    public Long getRRoomTypeId() {
        return this.rRoomTypeId;
    }

    @Override
    public HtlCodePrice setRRoomTypeId(Long rRoomTypeId) {
        this.rRoomTypeId = rRoomTypeId;
        return this;
    }

    @Override
    public Boolean getIsHasbrek() {
        return this.isHasbrek;
    }

    @Override
    public HtlCodePrice setIsHasbrek(Boolean isHasbrek) {
        this.isHasbrek = isHasbrek;
        return this;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public HtlCodePrice setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    @Override
    public String getZSigma() {
        return this.zSigma;
    }

    @Override
    public HtlCodePrice setZSigma(String zSigma) {
        this.zSigma = zSigma;
        return this;
    }

    @Override
    public String getZLanguage() {
        return this.zLanguage;
    }

    @Override
    public HtlCodePrice setZLanguage(String zLanguage) {
        this.zLanguage = zLanguage;
        return this;
    }

    @Override
    public String getZCreateBy() {
        return this.zCreateBy;
    }

    @Override
    public HtlCodePrice setZCreateBy(String zCreateBy) {
        this.zCreateBy = zCreateBy;
        return this;
    }

    @Override
    public LocalDateTime getZCreateTime() {
        return this.zCreateTime;
    }

    @Override
    public HtlCodePrice setZCreateTime(LocalDateTime zCreateTime) {
        this.zCreateTime = zCreateTime;
        return this;
    }

    @Override
    public String getZUpdateBy() {
        return this.zUpdateBy;
    }

    @Override
    public HtlCodePrice setZUpdateBy(String zUpdateBy) {
        this.zUpdateBy = zUpdateBy;
        return this;
    }

    @Override
    public LocalDateTime getZUpdateTime() {
        return this.zUpdateTime;
    }

    @Override
    public HtlCodePrice setZUpdateTime(LocalDateTime zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HtlCodePrice (");

        sb.append(pkId);
        sb.append(", ").append(dBkPrice);
        sb.append(", ").append(dPrice);
        sb.append(", ").append(sName);
        sb.append(", ").append(sCode);
        sb.append(", ").append(jConfig);
        sb.append(", ").append(rltMarket);
        sb.append(", ").append(rltPricecat);
        sb.append(", ").append(rltSource);
        sb.append(", ").append(rHotelId);
        sb.append(", ").append(rRoomTypeId);
        sb.append(", ").append(isHasbrek);
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
    public void from(IHtlCodePrice from) {
        setPkId(from.getPkId());
        setDBkPrice(from.getDBkPrice());
        setDPrice(from.getDPrice());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setJConfig(from.getJConfig());
        setRltMarket(from.getRltMarket());
        setRltPricecat(from.getRltPricecat());
        setRltSource(from.getRltSource());
        setRHotelId(from.getRHotelId());
        setRRoomTypeId(from.getRRoomTypeId());
        setIsHasbrek(from.getIsHasbrek());
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
    public <E extends IHtlCodePrice> E into(E into) {
        into.from(this);
        return into;
    }

    public HtlCodePrice(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
