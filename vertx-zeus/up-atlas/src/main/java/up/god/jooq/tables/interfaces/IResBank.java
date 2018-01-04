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
public interface IResBank extends Serializable {

    /**
     * Setter for <code>DB_HTL.RES_BANK.PK_ID</code>. uniqueId,PK_ID
     */
    public IResBank setPkId(Long value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.PK_ID</code>. uniqueId,PK_ID
     */
    public Long getPkId();

    /**
     * Setter for <code>DB_HTL.RES_BANK.T_ADDRESS</code>. address,T_ADDRESS
     */
    public IResBank setTAddress(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.T_ADDRESS</code>. address,T_ADDRESS
     */
    public String getTAddress();

    /**
     * Setter for <code>DB_HTL.RES_BANK.T_COMMENT</code>. comment,T_COMMENT
     */
    public IResBank setTComment(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.T_COMMENT</code>. comment,T_COMMENT
     */
    public String getTComment();

    /**
     * Setter for <code>DB_HTL.RES_BANK.T_STREET</code>. street,T_STREET
     */
    public IResBank setTStreet(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.T_STREET</code>. street,T_STREET
     */
    public String getTStreet();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_NAME</code>. name,S_NAME
     */
    public IResBank setSName(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_NAME</code>. name,S_NAME
     */
    public String getSName();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_CODE</code>. code,S_CODE
     */
    public IResBank setSCode(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_BIC</code>. 相当于Swift Code，基本银行代码，全球广泛使用,bic,S_BIC
     */
    public IResBank setSBic(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_BIC</code>. 相当于Swift Code，基本银行代码，全球广泛使用,bic,S_BIC
     */
    public String getSBic();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_CITY</code>. city,S_CITY
     */
    public IResBank setSCity(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_CITY</code>. city,S_CITY
     */
    public String getSCity();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_COUNTRY</code>. country,S_COUNTRY
     */
    public IResBank setSCountry(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_COUNTRY</code>. country,S_COUNTRY
     */
    public String getSCountry();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_DISTINCT</code>. distinct,S_DISTINCT
     */
    public IResBank setSDistinct(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_DISTINCT</code>. distinct,S_DISTINCT
     */
    public String getSDistinct();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_EMAIL</code>. 银行Email,email,S_EMAIL
     */
    public IResBank setSEmail(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_EMAIL</code>. 银行Email,email,S_EMAIL
     */
    public String getSEmail();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_FAX</code>. 传真号,fax,S_FAX
     */
    public IResBank setSFax(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_FAX</code>. 传真号,fax,S_FAX
     */
    public String getSFax();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_PHONE</code>. 客服电话,phone,S_PHONE
     */
    public IResBank setSPhone(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_PHONE</code>. 客服电话,phone,S_PHONE
     */
    public String getSPhone();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_STATE</code>. state,S_STATE
     */
    public IResBank setSState(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_STATE</code>. state,S_STATE
     */
    public String getSState();

    /**
     * Setter for <code>DB_HTL.RES_BANK.S_ZIP</code>. 邮政编码,zip,S_ZIP
     */
    public IResBank setSZip(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.S_ZIP</code>. 邮政编码,zip,S_ZIP
     */
    public String getSZip();

    /**
     * Setter for <code>DB_HTL.RES_BANK.J_CONFIG</code>. config,J_CONFIG
     */
    public IResBank setJConfig(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.J_CONFIG</code>. config,J_CONFIG
     */
    public String getJConfig();

    /**
     * Setter for <code>DB_HTL.RES_BANK.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IResBank setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public IResBank setZSigma(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public String getZSigma();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public IResBank setZLanguage(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public String getZLanguage();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public IResBank setZCreateBy(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public String getZCreateBy();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public IResBank setZCreateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public LocalDateTime getZCreateTime();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public IResBank setZUpdateBy(String value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public String getZUpdateBy();

    /**
     * Setter for <code>DB_HTL.RES_BANK.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public IResBank setZUpdateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.RES_BANK.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public LocalDateTime getZUpdateTime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IResBank
     */
    public void from(up.god.jooq.tables.interfaces.IResBank from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IResBank
     */
    public <E extends up.god.jooq.tables.interfaces.IResBank> E into(E into);

    default IResBank fromJson(io.vertx.core.json.JsonObject json) {
        setPkId(json.getLong("PK_ID"));
        setTAddress(json.getString("T_ADDRESS"));
        setTComment(json.getString("T_COMMENT"));
        setTStreet(json.getString("T_STREET"));
        setSName(json.getString("S_NAME"));
        setSCode(json.getString("S_CODE"));
        setSBic(json.getString("S_BIC"));
        setSCity(json.getString("S_CITY"));
        setSCountry(json.getString("S_COUNTRY"));
        setSDistinct(json.getString("S_DISTINCT"));
        setSEmail(json.getString("S_EMAIL"));
        setSFax(json.getString("S_FAX"));
        setSPhone(json.getString("S_PHONE"));
        setSState(json.getString("S_STATE"));
        setSZip(json.getString("S_ZIP"));
        setJConfig(json.getString("J_CONFIG"));
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
        json.put("T_ADDRESS",getTAddress());
        json.put("T_COMMENT",getTComment());
        json.put("T_STREET",getTStreet());
        json.put("S_NAME",getSName());
        json.put("S_CODE",getSCode());
        json.put("S_BIC",getSBic());
        json.put("S_CITY",getSCity());
        json.put("S_COUNTRY",getSCountry());
        json.put("S_DISTINCT",getSDistinct());
        json.put("S_EMAIL",getSEmail());
        json.put("S_FAX",getSFax());
        json.put("S_PHONE",getSPhone());
        json.put("S_STATE",getSState());
        json.put("S_ZIP",getSZip());
        json.put("J_CONFIG",getJConfig());
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
