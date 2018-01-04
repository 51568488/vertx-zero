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
public interface IHtlHisRoomStatus extends Serializable {

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.PK_ID</code>. uniqueId,PK_ID
     */
    public IHtlHisRoomStatus setPkId(Long value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.PK_ID</code>. uniqueId,PK_ID
     */
    public Long getPkId();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_END_TIME</code>. endTime,D_END_TIME
     */
    public IHtlHisRoomStatus setDEndTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_END_TIME</code>. endTime,D_END_TIME
     */
    public LocalDateTime getDEndTime();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_PLANNED_TIME</code>. plannedTime,D_PLANNED_TIME
     */
    public IHtlHisRoomStatus setDPlannedTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_PLANNED_TIME</code>. plannedTime,D_PLANNED_TIME
     */
    public LocalDateTime getDPlannedTime();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_START_TIME</code>. startTime,D_START_TIME
     */
    public IHtlHisRoomStatus setDStartTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.D_START_TIME</code>. startTime,D_START_TIME
     */
    public LocalDateTime getDStartTime();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.T_COMMENT</code>. comment,T_COMMENT
     */
    public IHtlHisRoomStatus setTComment(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.T_COMMENT</code>. comment,T_COMMENT
     */
    public String getTComment();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_CODE</code>. code,S_CODE
     */
    public IHtlHisRoomStatus setSCode(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_OPERATOR</code>. operator,S_OPERATOR
     */
    public IHtlHisRoomStatus setSOperator(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_OPERATOR</code>. operator,S_OPERATOR
     */
    public String getSOperator();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_STATUS</code>. status,S_STATUS
     */
    public IHtlHisRoomStatus setSStatus(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.S_STATUS</code>. status,S_STATUS
     */
    public String getSStatus();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.J_CONFIG</code>. config,J_CONFIG
     */
    public IHtlHisRoomStatus setJConfig(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.J_CONFIG</code>. config,J_CONFIG
     */
    public String getJConfig();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.R_ROOM_ID</code>. roomId,R_ROOM_ID
     */
    public IHtlHisRoomStatus setRRoomId(Long value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.R_ROOM_ID</code>. roomId,R_ROOM_ID
     */
    public Long getRRoomId();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IHtlHisRoomStatus setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public IHtlHisRoomStatus setZSigma(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public String getZSigma();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public IHtlHisRoomStatus setZLanguage(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public String getZLanguage();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public IHtlHisRoomStatus setZCreateBy(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public String getZCreateBy();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public IHtlHisRoomStatus setZCreateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public LocalDateTime getZCreateTime();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public IHtlHisRoomStatus setZUpdateBy(String value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public String getZUpdateBy();

    /**
     * Setter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public IHtlHisRoomStatus setZUpdateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.HTL_HIS_ROOM_STATUS.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public LocalDateTime getZUpdateTime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IHtlHisRoomStatus
     */
    public void from(up.god.jooq.tables.interfaces.IHtlHisRoomStatus from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IHtlHisRoomStatus
     */
    public <E extends up.god.jooq.tables.interfaces.IHtlHisRoomStatus> E into(E into);

    default IHtlHisRoomStatus fromJson(io.vertx.core.json.JsonObject json) {
        setPkId(json.getLong("PK_ID"));
        // Omitting unrecognized type java.time.LocalDateTime for column D_END_TIME!
        // Omitting unrecognized type java.time.LocalDateTime for column D_PLANNED_TIME!
        // Omitting unrecognized type java.time.LocalDateTime for column D_START_TIME!
        setTComment(json.getString("T_COMMENT"));
        setSCode(json.getString("S_CODE"));
        setSOperator(json.getString("S_OPERATOR"));
        setSStatus(json.getString("S_STATUS"));
        setJConfig(json.getString("J_CONFIG"));
        setRRoomId(json.getLong("R_ROOM_ID"));
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
        // Omitting unrecognized type java.time.LocalDateTime for column D_PLANNED_TIME!
        // Omitting unrecognized type java.time.LocalDateTime for column D_START_TIME!
        json.put("T_COMMENT",getTComment());
        json.put("S_CODE",getSCode());
        json.put("S_OPERATOR",getSOperator());
        json.put("S_STATUS",getSStatus());
        json.put("J_CONFIG",getJConfig());
        json.put("R_ROOM_ID",getRRoomId());
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
