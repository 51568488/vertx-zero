/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record20;
import org.jooq.Row20;
import org.jooq.impl.UpdatableRecordImpl;

import up.god.jooq.tables.SecUser;
import up.god.jooq.tables.interfaces.ISecUser;


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
public class SecUserRecord extends UpdatableRecordImpl<SecUserRecord> implements Record20<String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime>, ISecUser {

    private static final long serialVersionUID = -21028106;

    /**
     * Setter for <code>DB_HTL.SEC_USER.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public SecUserRecord setPkId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public String getPkId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_CODE</code>. code,S_CODE
     */
    @Override
    public SecUserRecord setSCode(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_CODE</code>. code,S_CODE
     */
    @Override
    public String getSCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_ALIPAY</code>. 支付宝,alipay,S_ALIPAY
     */
    @Override
    public SecUserRecord setSAlipay(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_ALIPAY</code>. 支付宝,alipay,S_ALIPAY
     */
    @Override
    public String getSAlipay() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_EMAIL</code>. 个人Email,email,S_EMAIL
     */
    @Override
    public SecUserRecord setSEmail(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_EMAIL</code>. 个人Email,email,S_EMAIL
     */
    @Override
    public String getSEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_MOBILE</code>. 手机号,mobile,S_MOBILE
     */
    @Override
    public SecUserRecord setSMobile(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_MOBILE</code>. 手机号,mobile,S_MOBILE
     */
    @Override
    public String getSMobile() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_PASSWORD</code>. 登陆口令/密码,password,S_PASSWORD
     */
    @Override
    public SecUserRecord setSPassword(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_PASSWORD</code>. 登陆口令/密码,password,S_PASSWORD
     */
    @Override
    public String getSPassword() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_QQ</code>. 个人QQ号,qq,S_QQ
     */
    @Override
    public SecUserRecord setSQq(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_QQ</code>. 个人QQ号,qq,S_QQ
     */
    @Override
    public String getSQq() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_SECRET</code>. 专用私钥，OAuth中也可用,secret,S_SECRET
     */
    @Override
    public SecUserRecord setSSecret(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_SECRET</code>. 专用私钥，OAuth中也可用,secret,S_SECRET
     */
    @Override
    public String getSSecret() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_TAOBAO</code>. 淘宝,taobao,S_TAOBAO
     */
    @Override
    public SecUserRecord setSTaobao(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_TAOBAO</code>. 淘宝,taobao,S_TAOBAO
     */
    @Override
    public String getSTaobao() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    @Override
    public SecUserRecord setSUsername(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    @Override
    public String getSUsername() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_WECHAT</code>. 微信账号,wechat,S_WECHAT
     */
    @Override
    public SecUserRecord setSWechat(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_WECHAT</code>. 微信账号,wechat,S_WECHAT
     */
    @Override
    public String getSWechat() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.S_WEIBO</code>. 微博账号,weibo,S_WEIBO
     */
    @Override
    public SecUserRecord setSWeibo(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.S_WEIBO</code>. 微博账号,weibo,S_WEIBO
     */
    @Override
    public String getSWeibo() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public SecUserRecord setJConfig(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public String getJConfig() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public SecUserRecord setIsActive(Boolean value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public SecUserRecord setZSigma(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public String getZSigma() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public SecUserRecord setZLanguage(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public String getZLanguage() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public SecUserRecord setZCreateBy(String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public String getZCreateBy() {
        return (String) get(16);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public SecUserRecord setZCreateTime(LocalDateTime value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public LocalDateTime getZCreateTime() {
        return (LocalDateTime) get(17);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public SecUserRecord setZUpdateBy(String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public String getZUpdateBy() {
        return (String) get(18);
    }

    /**
     * Setter for <code>DB_HTL.SEC_USER.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public SecUserRecord setZUpdateTime(LocalDateTime value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.SEC_USER.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public LocalDateTime getZUpdateTime() {
        return (LocalDateTime) get(19);
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
    // Record20 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row20<String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, String, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row20) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SecUser.SEC_USER.PK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SecUser.SEC_USER.S_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SecUser.SEC_USER.S_ALIPAY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return SecUser.SEC_USER.S_EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return SecUser.SEC_USER.S_MOBILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return SecUser.SEC_USER.S_PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return SecUser.SEC_USER.S_QQ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return SecUser.SEC_USER.S_SECRET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return SecUser.SEC_USER.S_TAOBAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return SecUser.SEC_USER.S_USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return SecUser.SEC_USER.S_WECHAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return SecUser.SEC_USER.S_WEIBO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return SecUser.SEC_USER.J_CONFIG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field14() {
        return SecUser.SEC_USER.IS_ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return SecUser.SEC_USER.Z_SIGMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return SecUser.SEC_USER.Z_LANGUAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return SecUser.SEC_USER.Z_CREATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field18() {
        return SecUser.SEC_USER.Z_CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field19() {
        return SecUser.SEC_USER.Z_UPDATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field20() {
        return SecUser.SEC_USER.Z_UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getSCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getSAlipay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getSEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getSMobile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getSPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getSQq();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getSSecret();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getSTaobao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getSUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getSWechat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getSWeibo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getJConfig();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component14() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getZSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component16() {
        return getZLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component17() {
        return getZCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component18() {
        return getZCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component19() {
        return getZUpdateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component20() {
        return getZUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getSCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSAlipay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getSMobile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getSPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getSQq();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getSSecret();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getSTaobao();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getSUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getSWechat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getSWeibo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getJConfig();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value14() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getZSigma();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getZLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getZCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value18() {
        return getZCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value19() {
        return getZUpdateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value20() {
        return getZUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value1(String value) {
        setPkId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value2(String value) {
        setSCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value3(String value) {
        setSAlipay(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value4(String value) {
        setSEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value5(String value) {
        setSMobile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value6(String value) {
        setSPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value7(String value) {
        setSQq(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value8(String value) {
        setSSecret(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value9(String value) {
        setSTaobao(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value10(String value) {
        setSUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value11(String value) {
        setSWechat(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value12(String value) {
        setSWeibo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value13(String value) {
        setJConfig(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value14(Boolean value) {
        setIsActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value15(String value) {
        setZSigma(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value16(String value) {
        setZLanguage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value17(String value) {
        setZCreateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value18(LocalDateTime value) {
        setZCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value19(String value) {
        setZUpdateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord value20(LocalDateTime value) {
        setZUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecUserRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, Boolean value14, String value15, String value16, String value17, LocalDateTime value18, String value19, LocalDateTime value20) {
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
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISecUser from) {
        setPkId(from.getPkId());
        setSCode(from.getSCode());
        setSAlipay(from.getSAlipay());
        setSEmail(from.getSEmail());
        setSMobile(from.getSMobile());
        setSPassword(from.getSPassword());
        setSQq(from.getSQq());
        setSSecret(from.getSSecret());
        setSTaobao(from.getSTaobao());
        setSUsername(from.getSUsername());
        setSWechat(from.getSWechat());
        setSWeibo(from.getSWeibo());
        setJConfig(from.getJConfig());
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
    public <E extends ISecUser> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SecUserRecord
     */
    public SecUserRecord() {
        super(SecUser.SEC_USER);
    }

    /**
     * Create a detached, initialised SecUserRecord
     */
    public SecUserRecord(String pkId, String sCode, String sAlipay, String sEmail, String sMobile, String sPassword, String sQq, String sSecret, String sTaobao, String sUsername, String sWechat, String sWeibo, String jConfig, Boolean isActive, String zSigma, String zLanguage, String zCreateBy, LocalDateTime zCreateTime, String zUpdateBy, LocalDateTime zUpdateTime) {
        super(SecUser.SEC_USER);

        set(0, pkId);
        set(1, sCode);
        set(2, sAlipay);
        set(3, sEmail);
        set(4, sMobile);
        set(5, sPassword);
        set(6, sQq);
        set(7, sSecret);
        set(8, sTaobao);
        set(9, sUsername);
        set(10, sWechat);
        set(11, sWeibo);
        set(12, jConfig);
        set(13, isActive);
        set(14, zSigma);
        set(15, zLanguage);
        set(16, zCreateBy);
        set(17, zCreateTime);
        set(18, zUpdateBy);
        set(19, zUpdateTime);
    }
}
