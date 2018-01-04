/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * VIEW
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IVHtlAddress extends Serializable {

    /**
     * Setter for <code>DB_HTL.v_htl_address.PK_ID</code>. uniqueId,PK_ID
     */
    public IVHtlAddress setPkId(Long value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.PK_ID</code>. uniqueId,PK_ID
     */
    public Long getPkId();

    /**
     * Setter for <code>DB_HTL.v_htl_address.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public IVHtlAddress setRHotelId(Long value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public Long getRHotelId();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_NAME</code>. name,S_NAME
     */
    public IVHtlAddress setSName(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_NAME</code>. name,S_NAME
     */
    public String getSName();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_FULLNAME</code>. 地址全称，通过计算出来的,fullName,S_FULLNAME
     */
    public IVHtlAddress setSFullname(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_FULLNAME</code>. 地址全称，通过计算出来的,fullName,S_FULLNAME
     */
    public String getSFullname();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_CODE</code>. code,S_CODE
     */
    public IVHtlAddress setSCode(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_STREET1</code>. street1,S_STREET1
     */
    public IVHtlAddress setSStreet1(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_STREET1</code>. street1,S_STREET1
     */
    public String getSStreet1();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_STREET2</code>. street2,S_STREET2
     */
    public IVHtlAddress setSStreet2(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_STREET2</code>. street2,S_STREET2
     */
    public String getSStreet2();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_STREET3</code>. street3,S_STREET3
     */
    public IVHtlAddress setSStreet3(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_STREET3</code>. street3,S_STREET3
     */
    public String getSStreet3();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_ADDRESS</code>. address,S_ADDRESS
     */
    public IVHtlAddress setSAddress(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_ADDRESS</code>. address,S_ADDRESS
     */
    public String getSAddress();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_ZIP</code>. zip,S_ZIP
     */
    public IVHtlAddress setSZip(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_ZIP</code>. zip,S_ZIP
     */
    public String getSZip();

    /**
     * Setter for <code>DB_HTL.v_htl_address.R_DISTINCT_ID</code>. distinctId,R_DISTINCT_ID
     */
    public IVHtlAddress setRDistinctId(Long value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.R_DISTINCT_ID</code>. distinctId,R_DISTINCT_ID
     */
    public Long getRDistinctId();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_DISTINCT</code>. distinct,S_DISTINCT
     */
    public IVHtlAddress setSDistinct(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_DISTINCT</code>. distinct,S_DISTINCT
     */
    public String getSDistinct();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_CITY</code>. city,S_CITY
     */
    public IVHtlAddress setSCity(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_CITY</code>. city,S_CITY
     */
    public String getSCity();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_STATE</code>. state,S_STATE
     */
    public IVHtlAddress setSState(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_STATE</code>. state,S_STATE
     */
    public String getSState();

    /**
     * Setter for <code>DB_HTL.v_htl_address.S_COUNTRY</code>. country,S_COUNTRY
     */
    public IVHtlAddress setSCountry(String value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.S_COUNTRY</code>. country,S_COUNTRY
     */
    public String getSCountry();

    /**
     * Setter for <code>DB_HTL.v_htl_address.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IVHtlAddress setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.v_htl_address.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IVHtlAddress
     */
    public void from(up.god.jooq.tables.interfaces.IVHtlAddress from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IVHtlAddress
     */
    public <E extends up.god.jooq.tables.interfaces.IVHtlAddress> E into(E into);

    default IVHtlAddress fromJson(io.vertx.core.json.JsonObject json) {
        setPkId(json.getLong("PK_ID"));
        setRHotelId(json.getLong("R_HOTEL_ID"));
        setSName(json.getString("S_NAME"));
        setSFullname(json.getString("S_FULLNAME"));
        setSCode(json.getString("S_CODE"));
        setSStreet1(json.getString("S_STREET1"));
        setSStreet2(json.getString("S_STREET2"));
        setSStreet3(json.getString("S_STREET3"));
        setSAddress(json.getString("S_ADDRESS"));
        setSZip(json.getString("S_ZIP"));
        setRDistinctId(json.getLong("R_DISTINCT_ID"));
        setSDistinct(json.getString("S_DISTINCT"));
        setSCity(json.getString("S_CITY"));
        setSState(json.getString("S_STATE"));
        setSCountry(json.getString("S_COUNTRY"));
        setIsActive(json.getBoolean("IS_ACTIVE"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("PK_ID",getPkId());
        json.put("R_HOTEL_ID",getRHotelId());
        json.put("S_NAME",getSName());
        json.put("S_FULLNAME",getSFullname());
        json.put("S_CODE",getSCode());
        json.put("S_STREET1",getSStreet1());
        json.put("S_STREET2",getSStreet2());
        json.put("S_STREET3",getSStreet3());
        json.put("S_ADDRESS",getSAddress());
        json.put("S_ZIP",getSZip());
        json.put("R_DISTINCT_ID",getRDistinctId());
        json.put("S_DISTINCT",getSDistinct());
        json.put("S_CITY",getSCity());
        json.put("S_STATE",getSState());
        json.put("S_COUNTRY",getSCountry());
        json.put("IS_ACTIVE",getIsActive());
        return json;
    }

}
