/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.impl.TableRecordImpl;

import up.god.jooq.tables.VVirCooperation;
import up.god.jooq.tables.interfaces.IVVirCooperation;


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
public class VVirCooperationRecord extends TableRecordImpl<VVirCooperationRecord> implements IVVirCooperation {

    private static final long serialVersionUID = -957345191;

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public VVirCooperationRecord setPkId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public Long getPkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.R_COMPANY_ID</code>. companyId,R_COMPANY_ID
     */
    @Override
    public VVirCooperationRecord setRCompanyId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.R_COMPANY_ID</code>. companyId,R_COMPANY_ID
     */
    @Override
    public Long getRCompanyId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_CODE</code>. 合作伙伴代码,code,S_CODE
     */
    @Override
    public VVirCooperationRecord setSCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_CODE</code>. 合作伙伴代码,code,S_CODE
     */
    @Override
    public String getSCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_NAME</code>. 名称：口语化名称,name,S_NAME
     */
    @Override
    public VVirCooperationRecord setSName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_NAME</code>. 名称：口语化名称,name,S_NAME
     */
    @Override
    public String getSName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_CREDIT_CODE</code>. 凭证代码,creditCode,S_CREDIT_CODE
     */
    @Override
    public VVirCooperationRecord setSCreditCode(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_CREDIT_CODE</code>. 凭证代码,creditCode,S_CREDIT_CODE
     */
    @Override
    public String getSCreditCode() {
        return (String) get(4);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.RLT_CREDIT_ITEM</code>. 凭证项，关联Tabular,creditItem,RLT_CREDIT_ITEM
     */
    @Override
    public VVirCooperationRecord setRltCreditItem(Long value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.RLT_CREDIT_ITEM</code>. 凭证项，关联Tabular,creditItem,RLT_CREDIT_ITEM
     */
    @Override
    public Long getRltCreditItem() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.T2_S_NAME</code>. name,S_NAME
     */
    @Override
    public VVirCooperationRecord setT2SName(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.T2_S_NAME</code>. name,S_NAME
     */
    @Override
    public String getT2SName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_CONTACT</code>. 联系人姓名,contact,S_CONTACT
     */
    @Override
    public VVirCooperationRecord setSContact(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_CONTACT</code>. 联系人姓名,contact,S_CONTACT
     */
    @Override
    public String getSContact() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_SIGN_NAME_ID</code>. 签单人ID,signNameId,S_SIGN_NAME_ID
     */
    @Override
    public VVirCooperationRecord setSSignNameId(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_SIGN_NAME_ID</code>. 签单人ID,signNameId,S_SIGN_NAME_ID
     */
    @Override
    public String getSSignNameId() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_SIGN_NAME</code>. 签单人姓名,signName,S_SIGN_NAME
     */
    @Override
    public VVirCooperationRecord setSSignName(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_SIGN_NAME</code>. 签单人姓名,signName,S_SIGN_NAME
     */
    @Override
    public String getSSignName() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_INVOICE_TITLE</code>. 发票抬头,invoiceTitle,S_INVOICE_TITLE
     */
    @Override
    public VVirCooperationRecord setSInvoiceTitle(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_INVOICE_TITLE</code>. 发票抬头,invoiceTitle,S_INVOICE_TITLE
     */
    @Override
    public String getSInvoiceTitle() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.D_CREDIT_START</code>. 凭证开始时间,creditStart,D_CREDIT_START
     */
    @Override
    public VVirCooperationRecord setDCreditStart(LocalDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.D_CREDIT_START</code>. 凭证开始时间,creditStart,D_CREDIT_START
     */
    @Override
    public LocalDateTime getDCreditStart() {
        return (LocalDateTime) get(11);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.D_CREDIT_END</code>. 凭证结束时间,creditEnd,D_CREDIT_END
     */
    @Override
    public VVirCooperationRecord setDCreditEnd(LocalDateTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.D_CREDIT_END</code>. 凭证结束时间,creditEnd,D_CREDIT_END
     */
    @Override
    public LocalDateTime getDCreditEnd() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_DISPLAY_NAME</code>. 显示名称：用于呈现以及页面显示,displayName,S_DISPLAY_NAME
     */
    @Override
    public VVirCooperationRecord setSDisplayName(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_DISPLAY_NAME</code>. 显示名称：用于呈现以及页面显示,displayName,S_DISPLAY_NAME
     */
    @Override
    public String getSDisplayName() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_REF</code>. 引用链接信息：一般用于Feed、在线应用时专用引用链接,ref,S_REF
     */
    @Override
    public VVirCooperationRecord setSRef(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_REF</code>. 引用链接信息：一般用于Feed、在线应用时专用引用链接,ref,S_REF
     */
    @Override
    public String getSRef() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_EMAIL</code>. 官方专用联系邮件地址,email,S_EMAIL
     */
    @Override
    public VVirCooperationRecord setSEmail(String value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_EMAIL</code>. 官方专用联系邮件地址,email,S_EMAIL
     */
    @Override
    public String getSEmail() {
        return (String) get(15);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_WEBSITE</code>. 官方网站网址,website,S_WEBSITE
     */
    @Override
    public VVirCooperationRecord setSWebsite(String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_WEBSITE</code>. 官方网站网址,website,S_WEBSITE
     */
    @Override
    public String getSWebsite() {
        return (String) get(16);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_FAX</code>. 传真号码,fax,S_FAX
     */
    @Override
    public VVirCooperationRecord setSFax(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_FAX</code>. 传真号码,fax,S_FAX
     */
    @Override
    public String getSFax() {
        return (String) get(17);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_BARCODE</code>. 条码信息,barCode,S_BARCODE
     */
    @Override
    public VVirCooperationRecord setSBarcode(String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_BARCODE</code>. 条码信息,barCode,S_BARCODE
     */
    @Override
    public String getSBarcode() {
        return (String) get(18);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_TZ</code>. 所在时区：该时区信息可以直接被程序解析,timeZone,S_TZ
     */
    @Override
    public VVirCooperationRecord setSTz(String value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_TZ</code>. 所在时区：该时区信息可以直接被程序解析,timeZone,S_TZ
     */
    @Override
    public String getSTz() {
        return (String) get(19);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_PHONE</code>. 联系电话（座机，备用联系电话）,phone,S_PHONE
     */
    @Override
    public VVirCooperationRecord setSPhone(String value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_PHONE</code>. 联系电话（座机，备用联系电话）,phone,S_PHONE
     */
    @Override
    public String getSPhone() {
        return (String) get(20);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_MOBILE</code>. 联系手机号（主要联系电话）,mobile,S_MOBILE
     */
    @Override
    public VVirCooperationRecord setSMobile(String value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_MOBILE</code>. 联系手机号（主要联系电话）,mobile,S_MOBILE
     */
    @Override
    public String getSMobile() {
        return (String) get(21);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_NOTIFY_EMAIL</code>. 邮件系统中的From专用邮件地址,notifyEmail,S_NOTIFY_EMAIL
     */
    @Override
    public VVirCooperationRecord setSNotifyEmail(String value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_NOTIFY_EMAIL</code>. 邮件系统中的From专用邮件地址,notifyEmail,S_NOTIFY_EMAIL
     */
    @Override
    public String getSNotifyEmail() {
        return (String) get(22);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.T_COMMENT</code>. 备注信息，合作伙伴相关简介,comment,T_COMMENT
     */
    @Override
    public VVirCooperationRecord setTComment(String value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.T_COMMENT</code>. 备注信息，合作伙伴相关简介,comment,T_COMMENT
     */
    @Override
    public String getTComment() {
        return (String) get(23);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.T_FEATURE</code>. 合作伙伴特征描述,feature,T_FEATURE
     */
    @Override
    public VVirCooperationRecord setTFeature(String value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.T_FEATURE</code>. 合作伙伴特征描述,feature,T_FEATURE
     */
    @Override
    public String getTFeature() {
        return (String) get(24);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.RLT_TYPE</code>. 合作伙伴类型,type,RLT_TYPE
     */
    @Override
    public VVirCooperationRecord setRltType(Long value) {
        set(25, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.RLT_TYPE</code>. 合作伙伴类型,type,RLT_TYPE
     */
    @Override
    public Long getRltType() {
        return (Long) get(25);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.T_LOCATION</code>. 直输地址，和下面的locationId选一使用,location,T_LOCATION
     */
    @Override
    public VVirCooperationRecord setTLocation(String value) {
        set(26, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.T_LOCATION</code>. 直输地址，和下面的locationId选一使用,location,T_LOCATION
     */
    @Override
    public String getTLocation() {
        return (String) get(26);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.R_LOCATION_ID</code>. 外键关联Location的ID,locationId,R_LOCATION_ID
     */
    @Override
    public VVirCooperationRecord setRLocationId(Long value) {
        set(27, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.R_LOCATION_ID</code>. 外键关联Location的ID,locationId,R_LOCATION_ID
     */
    @Override
    public Long getRLocationId() {
        return (Long) get(27);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.IS_RUN_UP</code>. 允许挂账？赊账？,runUp,IS_RUN_UP
     */
    @Override
    public VVirCooperationRecord setIsRunUp(Boolean value) {
        set(28, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.IS_RUN_UP</code>. 允许挂账？赊账？,runUp,IS_RUN_UP
     */
    @Override
    public Boolean getIsRunUp() {
        return (Boolean) get(28);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_RUN_UP_LIMIT</code>. 挂账限额,runUpLimit,S_RUN_UP_LIMIT
     */
    @Override
    public VVirCooperationRecord setSRunUpLimit(String value) {
        set(29, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_RUN_UP_LIMIT</code>. 挂账限额,runUpLimit,S_RUN_UP_LIMIT
     */
    @Override
    public String getSRunUpLimit() {
        return (String) get(29);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public VVirCooperationRecord setIsActive(Boolean value) {
        set(30, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(30);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_SIGNUP_TYPE</code>. 签名类型：E-SIGN,signType,S_SIGNUP_TYPE
     */
    @Override
    public VVirCooperationRecord setSSignupType(String value) {
        set(31, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_SIGNUP_TYPE</code>. 签名类型：E-SIGN,signType,S_SIGNUP_TYPE
     */
    @Override
    public String getSSignupType() {
        return (String) get(31);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.D_SIGNUP_EXPIRED</code>. 签名过期时间,signExpired,D_SIGNUP_EXPIRED
     */
    @Override
    public VVirCooperationRecord setDSignupExpired(LocalDateTime value) {
        set(32, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.D_SIGNUP_EXPIRED</code>. 签名过期时间,signExpired,D_SIGNUP_EXPIRED
     */
    @Override
    public LocalDateTime getDSignupExpired() {
        return (LocalDateTime) get(32);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.B_SIGNUP_TOKEN</code>. 签名专用TOKEN,signToken,B_SIGNUP_TOKEN
     */
    @Override
    public VVirCooperationRecord setBSignupToken(byte... value) {
        set(33, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.B_SIGNUP_TOKEN</code>. 签名专用TOKEN,signToken,B_SIGNUP_TOKEN
     */
    @Override
    public byte[] getBSignupToken() {
        return (byte[]) get(33);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.S_CONTRACT_CODE</code>. 外键关联contract的ID,保留,contractCode,S_CONTRACT_CODE
     */
    @Override
    public VVirCooperationRecord setSContractCode(String value) {
        set(34, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.S_CONTRACT_CODE</code>. 外键关联contract的ID,保留,contractCode,S_CONTRACT_CODE
     */
    @Override
    public String getSContractCode() {
        return (String) get(34);
    }

    /**
     * Setter for <code>DB_HTL.v_vir_cooperation.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public VVirCooperationRecord setZLanguage(String value) {
        set(35, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_vir_cooperation.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public String getZLanguage() {
        return (String) get(35);
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IVVirCooperation from) {
        setPkId(from.getPkId());
        setRCompanyId(from.getRCompanyId());
        setSCode(from.getSCode());
        setSName(from.getSName());
        setSCreditCode(from.getSCreditCode());
        setRltCreditItem(from.getRltCreditItem());
        setT2SName(from.getT2SName());
        setSContact(from.getSContact());
        setSSignNameId(from.getSSignNameId());
        setSSignName(from.getSSignName());
        setSInvoiceTitle(from.getSInvoiceTitle());
        setDCreditStart(from.getDCreditStart());
        setDCreditEnd(from.getDCreditEnd());
        setSDisplayName(from.getSDisplayName());
        setSRef(from.getSRef());
        setSEmail(from.getSEmail());
        setSWebsite(from.getSWebsite());
        setSFax(from.getSFax());
        setSBarcode(from.getSBarcode());
        setSTz(from.getSTz());
        setSPhone(from.getSPhone());
        setSMobile(from.getSMobile());
        setSNotifyEmail(from.getSNotifyEmail());
        setTComment(from.getTComment());
        setTFeature(from.getTFeature());
        setRltType(from.getRltType());
        setTLocation(from.getTLocation());
        setRLocationId(from.getRLocationId());
        setIsRunUp(from.getIsRunUp());
        setSRunUpLimit(from.getSRunUpLimit());
        setIsActive(from.getIsActive());
        setSSignupType(from.getSSignupType());
        setDSignupExpired(from.getDSignupExpired());
        setBSignupToken(from.getBSignupToken());
        setSContractCode(from.getSContractCode());
        setZLanguage(from.getZLanguage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IVVirCooperation> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VVirCooperationRecord
     */
    public VVirCooperationRecord() {
        super(VVirCooperation.V_VIR_COOPERATION);
    }

    /**
     * Create a detached, initialised VVirCooperationRecord
     */
    public VVirCooperationRecord(Long pkId, Long rCompanyId, String sCode, String sName, String sCreditCode, Long rltCreditItem, String t2SName, String sContact, String sSignNameId, String sSignName, String sInvoiceTitle, LocalDateTime dCreditStart, LocalDateTime dCreditEnd, String sDisplayName, String sRef, String sEmail, String sWebsite, String sFax, String sBarcode, String sTz, String sPhone, String sMobile, String sNotifyEmail, String tComment, String tFeature, Long rltType, String tLocation, Long rLocationId, Boolean isRunUp, String sRunUpLimit, Boolean isActive, String sSignupType, LocalDateTime dSignupExpired, byte[] bSignupToken, String sContractCode, String zLanguage) {
        super(VVirCooperation.V_VIR_COOPERATION);

        set(0, pkId);
        set(1, rCompanyId);
        set(2, sCode);
        set(3, sName);
        set(4, sCreditCode);
        set(5, rltCreditItem);
        set(6, t2SName);
        set(7, sContact);
        set(8, sSignNameId);
        set(9, sSignName);
        set(10, sInvoiceTitle);
        set(11, dCreditStart);
        set(12, dCreditEnd);
        set(13, sDisplayName);
        set(14, sRef);
        set(15, sEmail);
        set(16, sWebsite);
        set(17, sFax);
        set(18, sBarcode);
        set(19, sTz);
        set(20, sPhone);
        set(21, sMobile);
        set(22, sNotifyEmail);
        set(23, tComment);
        set(24, tFeature);
        set(25, rltType);
        set(26, tLocation);
        set(27, rLocationId);
        set(28, isRunUp);
        set(29, sRunUpLimit);
        set(30, isActive);
        set(31, sSignupType);
        set(32, dSignupExpired);
        set(33, bSignupToken);
        set(34, sContractCode);
        set(35, zLanguage);
    }
}
