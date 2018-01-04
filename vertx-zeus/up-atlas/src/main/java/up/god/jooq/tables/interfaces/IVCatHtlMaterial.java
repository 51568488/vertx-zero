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
public interface IVCatHtlMaterial extends Serializable {

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_HELP_CODE</code>. helpCode,S_HELP_CODE
     */
    public IVCatHtlMaterial setSHelpCode(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_HELP_CODE</code>. helpCode,S_HELP_CODE
     */
    public String getSHelpCode();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_PRODUCT_CODE</code>. productCode,S_PRODUCT_CODE
     */
    public IVCatHtlMaterial setSProductCode(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_PRODUCT_CODE</code>. productCode,S_PRODUCT_CODE
     */
    public String getSProductCode();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_PRODUCT_CAT</code>. productCat,S_PRODUCT_CAT
     */
    public IVCatHtlMaterial setSProductCat(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_PRODUCT_CAT</code>. productCat,S_PRODUCT_CAT
     */
    public String getSProductCat();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_UNIT</code>. unit,S_UNIT
     */
    public IVCatHtlMaterial setSUnit(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_UNIT</code>. unit,S_UNIT
     */
    public String getSUnit();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_REPOSITORY</code>. repository,S_REPOSITORY
     */
    public IVCatHtlMaterial setSRepository(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_REPOSITORY</code>. repository,S_REPOSITORY
     */
    public String getSRepository();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_PIC</code>. pic,S_PIC
     */
    public IVCatHtlMaterial setSPic(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_PIC</code>. pic,S_PIC
     */
    public String getSPic();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.L_MIN_BALANCE</code>. minBalance,L_MIN_BALANCE
     */
    public IVCatHtlMaterial setLMinBalance(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.L_MIN_BALANCE</code>. minBalance,L_MIN_BALANCE
     */
    public Long getLMinBalance();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.L_MAX_BALANCE</code>. maxBalance,L_MAX_BALANCE
     */
    public IVCatHtlMaterial setLMaxBalance(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.L_MAX_BALANCE</code>. maxBalance,L_MAX_BALANCE
     */
    public Long getLMaxBalance();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.L_QUALITYGPERIOD</code>. qualityGperiod,L_QUALITYGPERIOD
     */
    public IVCatHtlMaterial setLQualitygperiod(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.L_QUALITYGPERIOD</code>. qualityGperiod,L_QUALITYGPERIOD
     */
    public Long getLQualitygperiod();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.L_QUALITYAPERIOD</code>. qualityAperiod,L_QUALITYAPERIOD
     */
    public IVCatHtlMaterial setLQualityaperiod(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.L_QUALITYAPERIOD</code>. qualityAperiod,L_QUALITYAPERIOD
     */
    public Long getLQualityaperiod();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.V_INCOME_ID</code>. incomeId,V_INCOME_ID
     */
    public IVCatHtlMaterial setVIncomeId(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.V_INCOME_ID</code>. incomeId,V_INCOME_ID
     */
    public String getVIncomeId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.V_COST_ID</code>. costId,V_COST_ID
     */
    public IVCatHtlMaterial setVCostId(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.V_COST_ID</code>. costId,V_COST_ID
     */
    public String getVCostId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public IVCatHtlMaterial setRHotelId(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public Long getRHotelId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.A_S_TYPE</code>. type,S_TYPE
     */
    public IVCatHtlMaterial setASType(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.A_S_TYPE</code>. type,S_TYPE
     */
    public String getASType();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.V_PAYTERM_ID</code>. 【关联账单】该上挂的账单ID,payTermId,V_PAYTERM_ID
     */
    public IVCatHtlMaterial setVPaytermId(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.V_PAYTERM_ID</code>. 【关联账单】该上挂的账单ID,payTermId,V_PAYTERM_ID
     */
    public String getVPaytermId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.A_PK_ID</code>. uniqueId,PK_ID
     */
    public IVCatHtlMaterial setAPkId(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.A_PK_ID</code>. uniqueId,PK_ID
     */
    public Long getAPkId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.A_S_CODE</code>. code,S_CODE
     */
    public IVCatHtlMaterial setASCode(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.A_S_CODE</code>. code,S_CODE
     */
    public String getASCode();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.A_S_NAME</code>. name,S_NAME
     */
    public IVCatHtlMaterial setASName(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.A_S_NAME</code>. name,S_NAME
     */
    public String getASName();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public IVCatHtlMaterial setIsActive(Boolean value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public Boolean getIsActive();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.IS_PRODUCT</code>. product,IS_PRODUCT
     */
    public IVCatHtlMaterial setIsProduct(Boolean value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.IS_PRODUCT</code>. product,IS_PRODUCT
     */
    public Boolean getIsProduct();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.C_PK_ID</code>. uniqueId,PK_ID
     */
    public IVCatHtlMaterial setCPkId(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.C_PK_ID</code>. uniqueId,PK_ID
     */
    public String getCPkId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.S_NAME</code>. name,S_NAME
     */
    public IVCatHtlMaterial setSName(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.S_NAME</code>. name,S_NAME
     */
    public String getSName();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.C_S_CODE</code>. code,S_CODE
     */
    public IVCatHtlMaterial setCSCode(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.C_S_CODE</code>. code,S_CODE
     */
    public String getCSCode();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.C_S_TYPE</code>. REF：TABULAR -&gt; 类型本身分类，可指定不同模块,type,S_TYPE
     */
    public IVCatHtlMaterial setCSType(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.C_S_TYPE</code>. REF：TABULAR -&gt; 类型本身分类，可指定不同模块,type,S_TYPE
     */
    public String getCSType();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.I_LEFT</code>. left,I_LEFT
     */
    public IVCatHtlMaterial setILeft(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.I_LEFT</code>. left,I_LEFT
     */
    public Long getILeft();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.I_RIGHT</code>. right,I_RIGHT
     */
    public IVCatHtlMaterial setIRight(Long value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.I_RIGHT</code>. right,I_RIGHT
     */
    public Long getIRight();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.I_LEVEL</code>. level,I_LEVEL
     */
    public IVCatHtlMaterial setILevel(Integer value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.I_LEVEL</code>. level,I_LEVEL
     */
    public Integer getILevel();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.R_PARENT_ID</code>. parentId,R_PARENT_ID
     */
    public IVCatHtlMaterial setRParentId(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.R_PARENT_ID</code>. parentId,R_PARENT_ID
     */
    public String getRParentId();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public IVCatHtlMaterial setZSigma(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public String getZSigma();

    /**
     * Setter for <code>DB_HTL.v_cat_htl_material.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public IVCatHtlMaterial setZLanguage(String value);

    /**
     * Getter for <code>DB_HTL.v_cat_htl_material.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public String getZLanguage();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IVCatHtlMaterial
     */
    public void from(up.god.jooq.tables.interfaces.IVCatHtlMaterial from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IVCatHtlMaterial
     */
    public <E extends up.god.jooq.tables.interfaces.IVCatHtlMaterial> E into(E into);

    default IVCatHtlMaterial fromJson(io.vertx.core.json.JsonObject json) {
        setSHelpCode(json.getString("S_HELP_CODE"));
        setSProductCode(json.getString("S_PRODUCT_CODE"));
        setSProductCat(json.getString("S_PRODUCT_CAT"));
        setSUnit(json.getString("S_UNIT"));
        setSRepository(json.getString("S_REPOSITORY"));
        setSPic(json.getString("S_PIC"));
        setLMinBalance(json.getLong("L_MIN_BALANCE"));
        setLMaxBalance(json.getLong("L_MAX_BALANCE"));
        setLQualitygperiod(json.getLong("L_QUALITYGPERIOD"));
        setLQualityaperiod(json.getLong("L_QUALITYAPERIOD"));
        setVIncomeId(json.getString("V_INCOME_ID"));
        setVCostId(json.getString("V_COST_ID"));
        setRHotelId(json.getLong("R_HOTEL_ID"));
        setASType(json.getString("A_S_TYPE"));
        setVPaytermId(json.getString("V_PAYTERM_ID"));
        setAPkId(json.getLong("A_PK_ID"));
        setASCode(json.getString("A_S_CODE"));
        setASName(json.getString("A_S_NAME"));
        setIsActive(json.getBoolean("IS_ACTIVE"));
        setIsProduct(json.getBoolean("IS_PRODUCT"));
        setCPkId(json.getString("C_PK_ID"));
        setSName(json.getString("S_NAME"));
        setCSCode(json.getString("C_S_CODE"));
        setCSType(json.getString("C_S_TYPE"));
        setILeft(json.getLong("I_LEFT"));
        setIRight(json.getLong("I_RIGHT"));
        setILevel(json.getInteger("I_LEVEL"));
        setRParentId(json.getString("R_PARENT_ID"));
        setZSigma(json.getString("Z_SIGMA"));
        setZLanguage(json.getString("Z_LANGUAGE"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("S_HELP_CODE",getSHelpCode());
        json.put("S_PRODUCT_CODE",getSProductCode());
        json.put("S_PRODUCT_CAT",getSProductCat());
        json.put("S_UNIT",getSUnit());
        json.put("S_REPOSITORY",getSRepository());
        json.put("S_PIC",getSPic());
        json.put("L_MIN_BALANCE",getLMinBalance());
        json.put("L_MAX_BALANCE",getLMaxBalance());
        json.put("L_QUALITYGPERIOD",getLQualitygperiod());
        json.put("L_QUALITYAPERIOD",getLQualityaperiod());
        json.put("V_INCOME_ID",getVIncomeId());
        json.put("V_COST_ID",getVCostId());
        json.put("R_HOTEL_ID",getRHotelId());
        json.put("A_S_TYPE",getASType());
        json.put("V_PAYTERM_ID",getVPaytermId());
        json.put("A_PK_ID",getAPkId());
        json.put("A_S_CODE",getASCode());
        json.put("A_S_NAME",getASName());
        json.put("IS_ACTIVE",getIsActive());
        json.put("IS_PRODUCT",getIsProduct());
        json.put("C_PK_ID",getCPkId());
        json.put("S_NAME",getSName());
        json.put("C_S_CODE",getCSCode());
        json.put("C_S_TYPE",getCSType());
        json.put("I_LEFT",getILeft());
        json.put("I_RIGHT",getIRight());
        json.put("I_LEVEL",getILevel());
        json.put("R_PARENT_ID",getRParentId());
        json.put("Z_SIGMA",getZSigma());
        json.put("Z_LANGUAGE",getZLanguage());
        return json;
    }

}
