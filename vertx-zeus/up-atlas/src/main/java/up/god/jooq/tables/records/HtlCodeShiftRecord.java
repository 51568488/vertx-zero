/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.UpdatableRecordImpl;

import up.god.jooq.tables.HtlCodeShift;
import up.god.jooq.tables.interfaces.IHtlCodeShift;


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
public class HtlCodeShiftRecord extends UpdatableRecordImpl<HtlCodeShiftRecord> implements Record16<Long, LocalDateTime, LocalDateTime, String, String, String, Integer, Long, Long, Boolean, String, String, String, LocalDateTime, String, LocalDateTime>, IHtlCodeShift {

    private static final long serialVersionUID = 1546927799;

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public HtlCodeShiftRecord setPkId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public Long getPkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.D_END_TIME</code>. 交班结束时间,endTime,D_END_TIME
     */
    @Override
    public HtlCodeShiftRecord setDEndTime(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.D_END_TIME</code>. 交班结束时间,endTime,D_END_TIME
     */
    @Override
    public LocalDateTime getDEndTime() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.D_START_TIME</code>. 交班开始时间,startTime,D_START_TIME
     */
    @Override
    public HtlCodeShiftRecord setDStartTime(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.D_START_TIME</code>. 交班开始时间,startTime,D_START_TIME
     */
    @Override
    public LocalDateTime getDStartTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.S_NAME</code>. 【班别码名称】班别码名称,name,S_NAME
     */
    @Override
    public HtlCodeShiftRecord setSName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.S_NAME</code>. 【班别码名称】班别码名称,name,S_NAME
     */
    @Override
    public String getSName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.S_CODE</code>. code,S_CODE
     */
    @Override
    public HtlCodeShiftRecord setSCode(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.S_CODE</code>. code,S_CODE
     */
    @Override
    public String getSCode() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public HtlCodeShiftRecord setJConfig(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public String getJConfig() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.I_FLOW</code>. 交班顺序，从小到大,flow,I_FLOW
     */
    @Override
    public HtlCodeShiftRecord setIFlow(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.I_FLOW</code>. 交班顺序，从小到大,flow,I_FLOW
     */
    @Override
    public Integer getIFlow() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.RLT_SHIFTTYPE</code>. 班别类型,shiftType,RLT_SHIFTTYPE
     */
    @Override
    public HtlCodeShiftRecord setRltShifttype(Long value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.RLT_SHIFTTYPE</code>. 班别类型,shiftType,RLT_SHIFTTYPE
     */
    @Override
    public Long getRltShifttype() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    @Override
    public HtlCodeShiftRecord setRHotelId(Long value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    @Override
    public Long getRHotelId() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public HtlCodeShiftRecord setIsActive(Boolean value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(9);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public HtlCodeShiftRecord setZSigma(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public String getZSigma() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public HtlCodeShiftRecord setZLanguage(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public String getZLanguage() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public HtlCodeShiftRecord setZCreateBy(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public String getZCreateBy() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public HtlCodeShiftRecord setZCreateTime(LocalDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public LocalDateTime getZCreateTime() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public HtlCodeShiftRecord setZUpdateBy(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public String getZUpdateBy() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public HtlCodeShiftRecord setZUpdateTime(LocalDateTime value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public LocalDateTime getZUpdateTime() {
        return (LocalDateTime) get(15);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record16 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<Long, LocalDateTime, LocalDateTime, String, String, String, Integer, Long, Long, Boolean, String, String, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row16<Long, LocalDateTime, LocalDateTime, String, String, String, Integer, Long, Long, Boolean, String, String, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row16) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return HtlCodeShift.HTL_CODE_SHIFT.PK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field2() {
        return HtlCodeShift.HTL_CODE_SHIFT.D_END_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field3() {
        return HtlCodeShift.HTL_CODE_SHIFT.D_START_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return HtlCodeShift.HTL_CODE_SHIFT.S_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return HtlCodeShift.HTL_CODE_SHIFT.S_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return HtlCodeShift.HTL_CODE_SHIFT.J_CONFIG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return HtlCodeShift.HTL_CODE_SHIFT.I_FLOW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field8() {
        return HtlCodeShift.HTL_CODE_SHIFT.RLT_SHIFTTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field9() {
        return HtlCodeShift.HTL_CODE_SHIFT.R_HOTEL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field10() {
        return HtlCodeShift.HTL_CODE_SHIFT.IS_ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_CREATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field14() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_UPDATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field16() {
        return HtlCodeShift.HTL_CODE_SHIFT.Z_UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component2() {
        return getDEndTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component3() {
        return getDStartTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getSCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getJConfig();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getIFlow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component8() {
        return getRltShifttype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component9() {
        return getRHotelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component10() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getZSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getZLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getZCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component14() {
        return getZCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getZUpdateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component16() {
        return getZUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value2() {
        return getDEndTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value3() {
        return getDStartTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getSCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getJConfig();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getIFlow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value8() {
        return getRltShifttype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value9() {
        return getRHotelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value10() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getZSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getZLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getZCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value14() {
        return getZCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getZUpdateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value16() {
        return getZUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value1(Long value) {
        setPkId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value2(LocalDateTime value) {
        setDEndTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value3(LocalDateTime value) {
        setDStartTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value4(String value) {
        setSName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value5(String value) {
        setSCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value6(String value) {
        setJConfig(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value7(Integer value) {
        setIFlow(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value8(Long value) {
        setRltShifttype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value9(Long value) {
        setRHotelId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value10(Boolean value) {
        setIsActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value11(String value) {
        setZSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value12(String value) {
        setZLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value13(String value) {
        setZCreateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value14(LocalDateTime value) {
        setZCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value15(String value) {
        setZUpdateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord value16(LocalDateTime value) {
        setZUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlCodeShiftRecord values(Long value1, LocalDateTime value2, LocalDateTime value3, String value4, String value5, String value6, Integer value7, Long value8, Long value9, Boolean value10, String value11, String value12, String value13, LocalDateTime value14, String value15, LocalDateTime value16) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IHtlCodeShift from) {
        setPkId(from.getPkId());
        setDEndTime(from.getDEndTime());
        setDStartTime(from.getDStartTime());
        setSName(from.getSName());
        setSCode(from.getSCode());
        setJConfig(from.getJConfig());
        setIFlow(from.getIFlow());
        setRltShifttype(from.getRltShifttype());
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
    public <E extends IHtlCodeShift> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HtlCodeShiftRecord
     */
    public HtlCodeShiftRecord() {
        super(HtlCodeShift.HTL_CODE_SHIFT);
    }

    /**
     * Create a detached, initialised HtlCodeShiftRecord
     */
    public HtlCodeShiftRecord(Long pkId, LocalDateTime dEndTime, LocalDateTime dStartTime, String sName, String sCode, String jConfig, Integer iFlow, Long rltShifttype, Long rHotelId, Boolean isActive, String zSigma, String zLanguage, String zCreateBy, LocalDateTime zCreateTime, String zUpdateBy, LocalDateTime zUpdateTime) {
        super(HtlCodeShift.HTL_CODE_SHIFT);

        set(0, pkId);
        set(1, dEndTime);
        set(2, dStartTime);
        set(3, sName);
        set(4, sCode);
        set(5, jConfig);
        set(6, iFlow);
        set(7, rltShifttype);
        set(8, rHotelId);
        set(9, isActive);
        set(10, zSigma);
        set(11, zLanguage);
        set(12, zCreateBy);
        set(13, zCreateTime);
        set(14, zUpdateBy);
        set(15, zUpdateTime);
    }
}
