/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import up.god.jooq.tables.HtlTraveler;
import up.god.jooq.tables.interfaces.IHtlTraveler;


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
public class HtlTravelerRecord extends UpdatableRecordImpl<HtlTravelerRecord> implements IHtlTraveler {

    private static final long serialVersionUID = -1356054736;

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public HtlTravelerRecord setPkId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public String getPkId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.D_BIRTHDAY</code>. birthday,D_BIRTHDAY
     */
    @Override
    public HtlTravelerRecord setDBirthday(LocalDate value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.D_BIRTHDAY</code>. birthday,D_BIRTHDAY
     */
    @Override
    public LocalDate getDBirthday() {
        return (LocalDate) get(1);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.T_ADDRESS</code>. address,T_ADDRESS
     */
    @Override
    public HtlTravelerRecord setTAddress(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.T_ADDRESS</code>. address,T_ADDRESS
     */
    @Override
    public String getTAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.V_HOTEL_ID</code>. hotelId,V_HOTEL_ID
     */
    @Override
    public HtlTravelerRecord setVHotelId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.V_HOTEL_ID</code>. hotelId,V_HOTEL_ID
     */
    @Override
    public Long getVHotelId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_CODE</code>. code,S_CODE
     */
    @Override
    public HtlTravelerRecord setSCode(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_CODE</code>. code,S_CODE
     */
    @Override
    public String getSCode() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_COUNTRY</code>. country,S_COUNTRY
     */
    @Override
    public HtlTravelerRecord setSCountry(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_COUNTRY</code>. country,S_COUNTRY
     */
    @Override
    public String getSCountry() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_DRIVER_LIC</code>. driverLicense,S_DRIVER_LIC
     */
    @Override
    public HtlTravelerRecord setSDriverLic(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_DRIVER_LIC</code>. driverLicense,S_DRIVER_LIC
     */
    @Override
    public String getSDriverLic() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_IDC_NUM</code>. idcNumber,S_IDC_NUM
     */
    @Override
    public HtlTravelerRecord setSIdcNum(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_IDC_NUM</code>. idcNumber,S_IDC_NUM
     */
    @Override
    public String getSIdcNum() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_MOBILE</code>. mobile,S_MOBILE
     */
    @Override
    public HtlTravelerRecord setSMobile(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_MOBILE</code>. mobile,S_MOBILE
     */
    @Override
    public String getSMobile() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_NATION</code>. nation,S_NATION
     */
    @Override
    public HtlTravelerRecord setSNation(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_NATION</code>. nation,S_NATION
     */
    @Override
    public String getSNation() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_NICKNAME</code>. nickname,S_NICKNAME
     */
    @Override
    public HtlTravelerRecord setSNickname(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_NICKNAME</code>. nickname,S_NICKNAME
     */
    @Override
    public String getSNickname() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_NTV_PLACE</code>. nativePlace,S_NTV_PLACE
     */
    @Override
    public HtlTravelerRecord setSNtvPlace(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_NTV_PLACE</code>. nativePlace,S_NTV_PLACE
     */
    @Override
    public String getSNtvPlace() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_REALNAME</code>. realname,S_REALNAME
     */
    @Override
    public HtlTravelerRecord setSRealname(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_REALNAME</code>. realname,S_REALNAME
     */
    @Override
    public String getSRealname() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.S_SERIAL</code>. serial,S_SERIAL
     */
    @Override
    public HtlTravelerRecord setSSerial(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.S_SERIAL</code>. serial,S_SERIAL
     */
    @Override
    public String getSSerial() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public HtlTravelerRecord setJConfig(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public String getJConfig() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.I_INOCCUPS</code>. inoccups,I_INOCCUPS
     */
    @Override
    public HtlTravelerRecord setIInoccups(Integer value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.I_INOCCUPS</code>. inoccups,I_INOCCUPS
     */
    @Override
    public Integer getIInoccups() {
        return (Integer) get(15);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.RLT_IDC_TYPE</code>. idcType,RLT_IDC_TYPE
     */
    @Override
    public HtlTravelerRecord setRltIdcType(Long value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.RLT_IDC_TYPE</code>. idcType,RLT_IDC_TYPE
     */
    @Override
    public Long getRltIdcType() {
        return (Long) get(16);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.RLT_TRAVELER_STATUS</code>. status,RLT_TRAVELER_STATUS
     */
    @Override
    public HtlTravelerRecord setRltTravelerStatus(Long value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.RLT_TRAVELER_STATUS</code>. status,RLT_TRAVELER_STATUS
     */
    @Override
    public Long getRltTravelerStatus() {
        return (Long) get(17);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.R_OPERATOR_ID</code>. operator,R_OPERATOR_ID
     */
    @Override
    public HtlTravelerRecord setROperatorId(String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.R_OPERATOR_ID</code>. operator,R_OPERATOR_ID
     */
    @Override
    public String getROperatorId() {
        return (String) get(18);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.IS_MALE</code>. male,IS_MALE
     */
    @Override
    public HtlTravelerRecord setIsMale(Boolean value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.IS_MALE</code>. male,IS_MALE
     */
    @Override
    public Boolean getIsMale() {
        return (Boolean) get(19);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.IS_UPLOADED</code>. uploaded,IS_UPLOADED
     */
    @Override
    public HtlTravelerRecord setIsUploaded(Boolean value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.IS_UPLOADED</code>. uploaded,IS_UPLOADED
     */
    @Override
    public Boolean getIsUploaded() {
        return (Boolean) get(20);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public HtlTravelerRecord setIsActive(Boolean value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(21);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public HtlTravelerRecord setZSigma(String value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public String getZSigma() {
        return (String) get(22);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public HtlTravelerRecord setZLanguage(String value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public String getZLanguage() {
        return (String) get(23);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public HtlTravelerRecord setZCreateBy(String value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public String getZCreateBy() {
        return (String) get(24);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public HtlTravelerRecord setZCreateTime(LocalDateTime value) {
        set(25, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public LocalDateTime getZCreateTime() {
        return (LocalDateTime) get(25);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public HtlTravelerRecord setZUpdateBy(String value) {
        set(26, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public String getZUpdateBy() {
        return (String) get(26);
    }

    /**
     * Setter for <code>DB_HTL.HTL_TRAVELER.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public HtlTravelerRecord setZUpdateTime(LocalDateTime value) {
        set(27, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_TRAVELER.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public LocalDateTime getZUpdateTime() {
        return (LocalDateTime) get(27);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IHtlTraveler from) {
        setPkId(from.getPkId());
        setDBirthday(from.getDBirthday());
        setTAddress(from.getTAddress());
        setVHotelId(from.getVHotelId());
        setSCode(from.getSCode());
        setSCountry(from.getSCountry());
        setSDriverLic(from.getSDriverLic());
        setSIdcNum(from.getSIdcNum());
        setSMobile(from.getSMobile());
        setSNation(from.getSNation());
        setSNickname(from.getSNickname());
        setSNtvPlace(from.getSNtvPlace());
        setSRealname(from.getSRealname());
        setSSerial(from.getSSerial());
        setJConfig(from.getJConfig());
        setIInoccups(from.getIInoccups());
        setRltIdcType(from.getRltIdcType());
        setRltTravelerStatus(from.getRltTravelerStatus());
        setROperatorId(from.getROperatorId());
        setIsMale(from.getIsMale());
        setIsUploaded(from.getIsUploaded());
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
    public <E extends IHtlTraveler> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HtlTravelerRecord
     */
    public HtlTravelerRecord() {
        super(HtlTraveler.HTL_TRAVELER);
    }

    /**
     * Create a detached, initialised HtlTravelerRecord
     */
    public HtlTravelerRecord(String pkId, LocalDate dBirthday, String tAddress, Long vHotelId, String sCode, String sCountry, String sDriverLic, String sIdcNum, String sMobile, String sNation, String sNickname, String sNtvPlace, String sRealname, String sSerial, String jConfig, Integer iInoccups, Long rltIdcType, Long rltTravelerStatus, String rOperatorId, Boolean isMale, Boolean isUploaded, Boolean isActive, String zSigma, String zLanguage, String zCreateBy, LocalDateTime zCreateTime, String zUpdateBy, LocalDateTime zUpdateTime) {
        super(HtlTraveler.HTL_TRAVELER);

        set(0, pkId);
        set(1, dBirthday);
        set(2, tAddress);
        set(3, vHotelId);
        set(4, sCode);
        set(5, sCountry);
        set(6, sDriverLic);
        set(7, sIdcNum);
        set(8, sMobile);
        set(9, sNation);
        set(10, sNickname);
        set(11, sNtvPlace);
        set(12, sRealname);
        set(13, sSerial);
        set(14, jConfig);
        set(15, iInoccups);
        set(16, rltIdcType);
        set(17, rltTravelerStatus);
        set(18, rOperatorId);
        set(19, isMale);
        set(20, isUploaded);
        set(21, isActive);
        set(22, zSigma);
        set(23, zLanguage);
        set(24, zCreateBy);
        set(25, zCreateTime);
        set(26, zUpdateBy);
        set(27, zUpdateTime);
    }
}
