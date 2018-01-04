/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.interfaces;


import java.io.Serializable;
import java.math.BigDecimal;
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
public interface IEcmProduct extends Serializable {

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.PK_ID</code>. uniqueId,PK_ID
     */
    public IEcmProduct setPkId(Long value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.PK_ID</code>. uniqueId,PK_ID
     */
    public Long getPkId();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.D_MARKET_PRICE</code>. 市场价,marketPrice,D_MARKET_PRICE
     */
    public IEcmProduct setDMarketPrice(BigDecimal value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.D_MARKET_PRICE</code>. 市场价,marketPrice,D_MARKET_PRICE
     */
    public BigDecimal getDMarketPrice();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.D_SHOP_PRICE</code>. 在线购买价格E-Shop价,shopPrice,D_SHOP_PRICE
     */
    public IEcmProduct setDShopPrice(BigDecimal value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.D_SHOP_PRICE</code>. 在线购买价格E-Shop价,shopPrice,D_SHOP_PRICE
     */
    public BigDecimal getDShopPrice();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.D_USER_PRICE</code>. 普通用户价格,userPrice,D_USER_PRICE
     */
    public IEcmProduct setDUserPrice(BigDecimal value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.D_USER_PRICE</code>. 普通用户价格,userPrice,D_USER_PRICE
     */
    public BigDecimal getDUserPrice();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.D_VIP_PRICE</code>. 会员价,vipPrice,D_VIP_PRICE
     */
    public IEcmProduct setDVipPrice(BigDecimal value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.D_VIP_PRICE</code>. 会员价,vipPrice,D_VIP_PRICE
     */
    public BigDecimal getDVipPrice();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.L_CLICK</code>. 点击数,click,L_CLICK
     */
    public IEcmProduct setLClick(Long value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.L_CLICK</code>. 点击数,click,L_CLICK
     */
    public Long getLClick();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.T_COMMENT</code>. comment,T_COMMENT
     */
    public IEcmProduct setTComment(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.T_COMMENT</code>. comment,T_COMMENT
     */
    public String getTComment();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.S_CODE</code>. code,S_CODE
     */
    public IEcmProduct setSCode(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.S_GOOD_NO</code>. 货号,goodNo,S_GOOD_NO
     */
    public IEcmProduct setSGoodNo(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.S_GOOD_NO</code>. 货号,goodNo,S_GOOD_NO
     */
    public String getSGoodNo();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.S_MODEL</code>. 产品型号,model,S_MODEL
     */
    public IEcmProduct setSModel(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.S_MODEL</code>. 产品型号,model,S_MODEL
     */
    public String getSModel();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.S_SIZE</code>. 尺寸,size,S_SIZE
     */
    public IEcmProduct setSSize(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.S_SIZE</code>. 尺寸,size,S_SIZE
     */
    public String getSSize();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.J_CONFIG</code>. config,J_CONFIG
     */
    public IEcmProduct setJConfig(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.J_CONFIG</code>. config,J_CONFIG
     */
    public String getJConfig();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.I_POINT</code>. 产品积分,point,I_POINT
     */
    public IEcmProduct setIPoint(Integer value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.I_POINT</code>. 产品积分,point,I_POINT
     */
    public Integer getIPoint();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.I_SALE_NUM</code>. 销售数量,saleNumber,I_SALE_NUM
     */
    public IEcmProduct setISaleNum(Integer value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.I_SALE_NUM</code>. 销售数量,saleNumber,I_SALE_NUM
     */
    public Integer getISaleNum();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.R_PRODUCT_ID</code>. productId,R_PRODUCT_ID
     */
    public IEcmProduct setRProductId(Long value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.R_PRODUCT_ID</code>. productId,R_PRODUCT_ID
     */
    public Long getRProductId();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.R_SHOP_ID</code>. shopId,R_SHOP_ID
     */
    public IEcmProduct setRShopId(Long value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.R_SHOP_ID</code>. shopId,R_SHOP_ID
     */
    public Long getRShopId();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.IS_CARRIAGE</code>. 运费？,carriage,IS_CARRIAGE
     */
    public IEcmProduct setIsCarriage(Boolean value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.IS_CARRIAGE</code>. 运费？,carriage,IS_CARRIAGE
     */
    public Boolean getIsCarriage();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IEcmProduct setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public IEcmProduct setZSigma(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public String getZSigma();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public IEcmProduct setZLanguage(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public String getZLanguage();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public IEcmProduct setZCreateBy(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public String getZCreateBy();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public IEcmProduct setZCreateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public LocalDateTime getZCreateTime();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public IEcmProduct setZUpdateBy(String value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public String getZUpdateBy();

    /**
     * Setter for <code>DB_HTL.ECM_PRODUCT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public IEcmProduct setZUpdateTime(LocalDateTime value);

    /**
     * Getter for <code>DB_HTL.ECM_PRODUCT.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public LocalDateTime getZUpdateTime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IEcmProduct
     */
    public void from(up.god.jooq.tables.interfaces.IEcmProduct from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IEcmProduct
     */
    public <E extends up.god.jooq.tables.interfaces.IEcmProduct> E into(E into);

    default IEcmProduct fromJson(io.vertx.core.json.JsonObject json) {
        setPkId(json.getLong("PK_ID"));
        // Omitting unrecognized type java.math.BigDecimal for column D_MARKET_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_SHOP_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_USER_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_VIP_PRICE!
        setLClick(json.getLong("L_CLICK"));
        setTComment(json.getString("T_COMMENT"));
        setSCode(json.getString("S_CODE"));
        setSGoodNo(json.getString("S_GOOD_NO"));
        setSModel(json.getString("S_MODEL"));
        setSSize(json.getString("S_SIZE"));
        setJConfig(json.getString("J_CONFIG"));
        setIPoint(json.getInteger("I_POINT"));
        setISaleNum(json.getInteger("I_SALE_NUM"));
        setRProductId(json.getLong("R_PRODUCT_ID"));
        setRShopId(json.getLong("R_SHOP_ID"));
        setIsCarriage(json.getBoolean("IS_CARRIAGE"));
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
        // Omitting unrecognized type java.math.BigDecimal for column D_MARKET_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_SHOP_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_USER_PRICE!
        // Omitting unrecognized type java.math.BigDecimal for column D_VIP_PRICE!
        json.put("L_CLICK",getLClick());
        json.put("T_COMMENT",getTComment());
        json.put("S_CODE",getSCode());
        json.put("S_GOOD_NO",getSGoodNo());
        json.put("S_MODEL",getSModel());
        json.put("S_SIZE",getSSize());
        json.put("J_CONFIG",getJConfig());
        json.put("I_POINT",getIPoint());
        json.put("I_SALE_NUM",getISaleNum());
        json.put("R_PRODUCT_ID",getRProductId());
        json.put("R_SHOP_ID",getRShopId());
        json.put("IS_CARRIAGE",getIsCarriage());
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
