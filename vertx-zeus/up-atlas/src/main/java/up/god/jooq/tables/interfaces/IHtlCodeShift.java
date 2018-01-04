/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.interfaces;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
public interface IHtlCodeShift extends Serializable {

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.PK_ID</code>. uniqueId,PK_ID
     */
    public IHtlCodeShift setPkId(Long value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.PK_ID</code>. uniqueId,PK_ID
     */
    public Long getPkId();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.D_END_TIME</code>. 交班结束时间,endTime,D_END_TIME
     */
    public IHtlCodeShift setDEndTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.D_END_TIME</code>. 交班结束时间,endTime,D_END_TIME
     */
    public LocalDateTime getDEndTime();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.D_START_TIME</code>. 交班开始时间,startTime,D_START_TIME
     */
    public IHtlCodeShift setDStartTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.D_START_TIME</code>. 交班开始时间,startTime,D_START_TIME
     */
    public LocalDateTime getDStartTime();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.S_NAME</code>. 【班别码名称】班别码名称,name,S_NAME
     */
    public IHtlCodeShift setSName(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.S_NAME</code>. 【班别码名称】班别码名称,name,S_NAME
     */
    public String getSName();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.S_CODE</code>. code,S_CODE
     */
    public IHtlCodeShift setSCode(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.J_CONFIG</code>. config,J_CONFIG
     */
    public IHtlCodeShift setJConfig(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.J_CONFIG</code>. config,J_CONFIG
     */
    public String getJConfig();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.I_FLOW</code>. 交班顺序，从小到大,flow,I_FLOW
     */
    public IHtlCodeShift setIFlow(Integer value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.I_FLOW</code>. 交班顺序，从小到大,flow,I_FLOW
     */
    public Integer getIFlow();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.RLT_SHIFTTYPE</code>. 班别类型,shiftType,RLT_SHIFTTYPE
     */
    public IHtlCodeShift setRltShifttype(Long value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.RLT_SHIFTTYPE</code>. 班别类型,shiftType,RLT_SHIFTTYPE
     */
    public Long getRltShifttype();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public IHtlCodeShift setRHotelId(Long value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public Long getRHotelId();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IHtlCodeShift setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public IHtlCodeShift setZSigma(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public String getZSigma();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public IHtlCodeShift setZLanguage(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public String getZLanguage();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public IHtlCodeShift setZCreateBy(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public String getZCreateBy();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public IHtlCodeShift setZCreateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public LocalDateTime getZCreateTime();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public IHtlCodeShift setZUpdateBy(String value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public String getZUpdateBy();

    /**
     * Setter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public IHtlCodeShift setZUpdateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_CODE_SHIFT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public LocalDateTime getZUpdateTime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IHtlCodeShift
     */
    public void from(up.god.jooq.tables.interfaces.IHtlCodeShift from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IHtlCodeShift
     */
    public <E extends up.god.jooq.tables.interfaces.IHtlCodeShift> E into(E into);

    default IHtlCodeShift fromJson(io.vertx.core.json.JsonObject json) {
        setPkId(json.getLong("PK_ID"));
        // Omitting unrecognized type java.time.LocalDateTime for column D_END_TIME!
        // Omitting unrecognized type java.time.LocalDateTime for column D_START_TIME!
        setSName(json.getString("S_NAME"));
        setSCode(json.getString("S_CODE"));
        setJConfig(json.getString("J_CONFIG"));
        setIFlow(json.getInteger("I_FLOW"));
        setRltShifttype(json.getLong("RLT_SHIFTTYPE"));
        setRHotelId(json.getLong("R_HOTEL_ID"));
        setIsActive(json.getBoolean("IS_ACTIVE"));
        setZSigma(json.getString("Z_SIGMA"));
        setZLanguage(json.getString("Z_LANGUAGE"));
        setZCreateBy(json.getString("Z_CREATE_BY"));
        // Omitting unrecognized type java.time.LocalDateTime for column Z_CREATE_TIME!
        setZUpdateBy(json.getString("Z_UPDATE_BY"));
        // Omitting unrecognized type java.time.LocalDateTime for column Z_UPDATE_TIME!
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("PK_ID",getPkId());
        // Omitting unrecognized type java.time.LocalDateTime for column D_END_TIME!
        // Omitting unrecognized type java.time.LocalDateTime for column D_START_TIME!
        json.put("S_NAME",getSName());
        json.put("S_CODE",getSCode());
        json.put("J_CONFIG",getJConfig());
        json.put("I_FLOW",getIFlow());
        json.put("RLT_SHIFTTYPE",getRltShifttype());
        json.put("R_HOTEL_ID",getRHotelId());
        json.put("IS_ACTIVE",getIsActive());
        json.put("Z_SIGMA",getZSigma());
        json.put("Z_LANGUAGE",getZLanguage());
        json.put("Z_CREATE_BY",getZCreateBy());
        // Omitting unrecognized type java.time.LocalDateTime for column Z_CREATE_TIME!
        json.put("Z_UPDATE_BY",getZUpdateBy());
        // Omitting unrecognized type java.time.LocalDateTime for column Z_UPDATE_TIME!
        return json;
    }

}
