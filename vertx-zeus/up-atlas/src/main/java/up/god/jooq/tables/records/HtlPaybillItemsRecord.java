/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import up.god.jooq.tables.HtlPaybillItems;
import up.god.jooq.tables.interfaces.IHtlPaybillItems;


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
public class HtlPaybillItemsRecord extends UpdatableRecordImpl<HtlPaybillItemsRecord> implements IHtlPaybillItems {

    private static final long serialVersionUID = 21903950;

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public HtlPaybillItemsRecord setPkId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public String getPkId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_AMOUNT_ACTUAL</code>. 【实际总价】实际给付结果,amountActual,D_AMOUNT_ACTUAL
     */
    @Override
    public HtlPaybillItemsRecord setDAmountActual(BigDecimal value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_AMOUNT_ACTUAL</code>. 【实际总价】实际给付结果,amountActual,D_AMOUNT_ACTUAL
     */
    @Override
    public BigDecimal getDAmountActual() {
        return (BigDecimal) get(1);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_AMOUNT_TOTAL</code>. 【总价】理论计算结果,amountTotal,D_AMOUNT_TOTAL
     */
    @Override
    public HtlPaybillItemsRecord setDAmountTotal(BigDecimal value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_AMOUNT_TOTAL</code>. 【总价】理论计算结果,amountTotal,D_AMOUNT_TOTAL
     */
    @Override
    public BigDecimal getDAmountTotal() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_FINISHED_DATE</code>. finishedDate,D_FINISHED_DATE
     */
    @Override
    public HtlPaybillItemsRecord setDFinishedDate(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_FINISHED_DATE</code>. finishedDate,D_FINISHED_DATE
     */
    @Override
    public LocalDateTime getDFinishedDate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_OPERATE_DATE</code>. operateDate,D_OPERATE_DATE
     */
    @Override
    public HtlPaybillItemsRecord setDOperateDate(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_OPERATE_DATE</code>. operateDate,D_OPERATE_DATE
     */
    @Override
    public LocalDateTime getDOperateDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_PRICE</code>. 【单价】商品的单价。,price,D_PRICE
     */
    @Override
    public HtlPaybillItemsRecord setDPrice(BigDecimal value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.D_PRICE</code>. 【单价】商品的单价。,price,D_PRICE
     */
    @Override
    public BigDecimal getDPrice() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.T_COMMENT</code>. 【备注】账单相关备注信息,comment,T_COMMENT
     */
    @Override
    public HtlPaybillItemsRecord setTComment(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.T_COMMENT</code>. 【备注】账单相关备注信息,comment,T_COMMENT
     */
    @Override
    public String getTComment() {
        return (String) get(6);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_CODE</code>. code,S_CODE
     */
    @Override
    public HtlPaybillItemsRecord setSCode(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_CODE</code>. code,S_CODE
     */
    @Override
    public String getSCode() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_BRIEF</code>. 【简单备注】,brief,S_BRIEF
     */
    @Override
    public HtlPaybillItemsRecord setSBrief(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_BRIEF</code>. 【简单备注】,brief,S_BRIEF
     */
    @Override
    public String getSBrief() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_EMPLOYEE_NO</code>. 【工号】,employeeNo,S_EMPLOYEE_NO
     */
    @Override
    public HtlPaybillItemsRecord setSEmployeeNo(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_EMPLOYEE_NO</code>. 【工号】,employeeNo,S_EMPLOYEE_NO
     */
    @Override
    public String getSEmployeeNo() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_MANUAL_NO</code>. 【账单手工单号】,manualNo,S_MANUAL_NO
     */
    @Override
    public HtlPaybillItemsRecord setSManualNo(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_MANUAL_NO</code>. 【账单手工单号】,manualNo,S_MANUAL_NO
     */
    @Override
    public String getSManualNo() {
        return (String) get(10);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_SERIAL</code>. 【账单唯一编号】系统内部使用,serial,S_SERIAL
     */
    @Override
    public HtlPaybillItemsRecord setSSerial(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_SERIAL</code>. 【账单唯一编号】系统内部使用,serial,S_SERIAL
     */
    @Override
    public String getSSerial() {
        return (String) get(11);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_SHIFT</code>. 【班次】保存结果，同房号字段,shift,S_SHIFT
     */
    @Override
    public HtlPaybillItemsRecord setSShift(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_SHIFT</code>. 【班次】保存结果，同房号字段,shift,S_SHIFT
     */
    @Override
    public String getSShift() {
        return (String) get(12);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_TRANSFER</code>. 【账单流转】,transfer,S_TRANSFER
     */
    @Override
    public HtlPaybillItemsRecord setSTransfer(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.S_TRANSFER</code>. 【账单流转】,transfer,S_TRANSFER
     */
    @Override
    public String getSTransfer() {
        return (String) get(13);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public HtlPaybillItemsRecord setJConfig(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.J_CONFIG</code>. config,J_CONFIG
     */
    @Override
    public String getJConfig() {
        return (String) get(14);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_LEFT</code>. left,I_LEFT
     */
    @Override
    public HtlPaybillItemsRecord setILeft(Long value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_LEFT</code>. left,I_LEFT
     */
    @Override
    public Long getILeft() {
        return (Long) get(15);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_LEVEL</code>. level,I_LEVEL
     */
    @Override
    public HtlPaybillItemsRecord setILevel(Integer value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_LEVEL</code>. level,I_LEVEL
     */
    @Override
    public Integer getILevel() {
        return (Integer) get(16);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_QUANTITY</code>. 【数量】商品数量,quantity,I_QUANTITY
     */
    @Override
    public HtlPaybillItemsRecord setIQuantity(Integer value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_QUANTITY</code>. 【数量】商品数量,quantity,I_QUANTITY
     */
    @Override
    public Integer getIQuantity() {
        return (Integer) get(17);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_RIGHT</code>. right,I_RIGHT
     */
    @Override
    public HtlPaybillItemsRecord setIRight(Long value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_RIGHT</code>. right,I_RIGHT
     */
    @Override
    public Long getIRight() {
        return (Long) get(18);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_SEQUENCE</code>. 序号,sequence,I_SEQUENCE
     */
    @Override
    public HtlPaybillItemsRecord setISequence(Integer value) {
        set(19, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.I_SEQUENCE</code>. 序号,sequence,I_SEQUENCE
     */
    @Override
    public Integer getISequence() {
        return (Integer) get(19);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.RLT_BILL_TYPE</code>. REF: SYS_TABULAR【账单类型】,billType,RLT_BILL_TYPE
     */
    @Override
    public HtlPaybillItemsRecord setRltBillType(Long value) {
        set(20, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.RLT_BILL_TYPE</code>. REF: SYS_TABULAR【账单类型】,billType,RLT_BILL_TYPE
     */
    @Override
    public Long getRltBillType() {
        return (Long) get(20);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.RLT_VALID</code>. 【账单状态】，如：拆帐后原始账单不为valid, bill.filter.type,valid,RLT_VALID
     */
    @Override
    public HtlPaybillItemsRecord setRltValid(Long value) {
        set(21, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.RLT_VALID</code>. 【账单状态】，如：拆帐后原始账单不为valid, bill.filter.type,valid,RLT_VALID
     */
    @Override
    public Long getRltValid() {
        return (Long) get(21);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PARENT_ID</code>. REF: HTL_PAYBILL_ITEMS 关联拆帐上级 ID,parentId,R_PARENT_ID
     */
    @Override
    public HtlPaybillItemsRecord setRParentId(String value) {
        set(22, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PARENT_ID</code>. REF: HTL_PAYBILL_ITEMS 关联拆帐上级 ID,parentId,R_PARENT_ID
     */
    @Override
    public String getRParentId() {
        return (String) get(22);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PAYBILL_ID</code>. REF: HTL_PAYBILL 关联账单ID,payBillId,R_PAYBILL_ID
     */
    @Override
    public HtlPaybillItemsRecord setRPaybillId(String value) {
        set(23, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PAYBILL_ID</code>. REF: HTL_PAYBILL 关联账单ID,payBillId,R_PAYBILL_ID
     */
    @Override
    public String getRPaybillId() {
        return (String) get(23);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PAYTERM_ID</code>. REF: ATM_PAYTERM【消费项】关联消费项ID,ptermId,R_PAYTERM_ID
     */
    @Override
    public HtlPaybillItemsRecord setRPaytermId(String value) {
        set(24, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.R_PAYTERM_ID</code>. REF: ATM_PAYTERM【消费项】关联消费项ID,ptermId,R_PAYTERM_ID
     */
    @Override
    public String getRPaytermId() {
        return (String) get(24);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_FINISHED</code>. 是否已结算,finished,IS_FINISHED
     */
    @Override
    public HtlPaybillItemsRecord setIsFinished(Boolean value) {
        set(25, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_FINISHED</code>. 是否已结算,finished,IS_FINISHED
     */
    @Override
    public Boolean getIsFinished() {
        return (Boolean) get(25);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_INCOME</code>. 【账单方向】，income为true入帐，否则为出帐,income,IS_INCOME
     */
    @Override
    public HtlPaybillItemsRecord setIsIncome(Boolean value) {
        set(26, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_INCOME</code>. 【账单方向】，income为true入帐，否则为出帐,income,IS_INCOME
     */
    @Override
    public Boolean getIsIncome() {
        return (Boolean) get(26);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public HtlPaybillItemsRecord setIsActive(Boolean value) {
        set(27, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(27);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public HtlPaybillItemsRecord setZSigma(String value) {
        set(28, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    @Override
    public String getZSigma() {
        return (String) get(28);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public HtlPaybillItemsRecord setZLanguage(String value) {
        set(29, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    @Override
    public String getZLanguage() {
        return (String) get(29);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public HtlPaybillItemsRecord setZCreateBy(String value) {
        set(30, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    @Override
    public String getZCreateBy() {
        return (String) get(30);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public HtlPaybillItemsRecord setZCreateTime(LocalDateTime value) {
        set(31, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    @Override
    public LocalDateTime getZCreateTime() {
        return (LocalDateTime) get(31);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public HtlPaybillItemsRecord setZUpdateBy(String value) {
        set(32, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    @Override
    public String getZUpdateBy() {
        return (String) get(32);
    }

    /**
     * Setter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public HtlPaybillItemsRecord setZUpdateTime(LocalDateTime value) {
        set(33, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.HTL_PAYBILL_ITEMS.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    @Override
    public LocalDateTime getZUpdateTime() {
        return (LocalDateTime) get(33);
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
    public void from(IHtlPaybillItems from) {
        setPkId(from.getPkId());
        setDAmountActual(from.getDAmountActual());
        setDAmountTotal(from.getDAmountTotal());
        setDFinishedDate(from.getDFinishedDate());
        setDOperateDate(from.getDOperateDate());
        setDPrice(from.getDPrice());
        setTComment(from.getTComment());
        setSCode(from.getSCode());
        setSBrief(from.getSBrief());
        setSEmployeeNo(from.getSEmployeeNo());
        setSManualNo(from.getSManualNo());
        setSSerial(from.getSSerial());
        setSShift(from.getSShift());
        setSTransfer(from.getSTransfer());
        setJConfig(from.getJConfig());
        setILeft(from.getILeft());
        setILevel(from.getILevel());
        setIQuantity(from.getIQuantity());
        setIRight(from.getIRight());
        setISequence(from.getISequence());
        setRltBillType(from.getRltBillType());
        setRltValid(from.getRltValid());
        setRParentId(from.getRParentId());
        setRPaybillId(from.getRPaybillId());
        setRPaytermId(from.getRPaytermId());
        setIsFinished(from.getIsFinished());
        setIsIncome(from.getIsIncome());
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
    public <E extends IHtlPaybillItems> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HtlPaybillItemsRecord
     */
    public HtlPaybillItemsRecord() {
        super(HtlPaybillItems.HTL_PAYBILL_ITEMS);
    }

    /**
     * Create a detached, initialised HtlPaybillItemsRecord
     */
    public HtlPaybillItemsRecord(String pkId, BigDecimal dAmountActual, BigDecimal dAmountTotal, LocalDateTime dFinishedDate, LocalDateTime dOperateDate, BigDecimal dPrice, String tComment, String sCode, String sBrief, String sEmployeeNo, String sManualNo, String sSerial, String sShift, String sTransfer, String jConfig, Long iLeft, Integer iLevel, Integer iQuantity, Long iRight, Integer iSequence, Long rltBillType, Long rltValid, String rParentId, String rPaybillId, String rPaytermId, Boolean isFinished, Boolean isIncome, Boolean isActive, String zSigma, String zLanguage, String zCreateBy, LocalDateTime zCreateTime, String zUpdateBy, LocalDateTime zUpdateTime) {
        super(HtlPaybillItems.HTL_PAYBILL_ITEMS);

        set(0, pkId);
        set(1, dAmountActual);
        set(2, dAmountTotal);
        set(3, dFinishedDate);
        set(4, dOperateDate);
        set(5, dPrice);
        set(6, tComment);
        set(7, sCode);
        set(8, sBrief);
        set(9, sEmployeeNo);
        set(10, sManualNo);
        set(11, sSerial);
        set(12, sShift);
        set(13, sTransfer);
        set(14, jConfig);
        set(15, iLeft);
        set(16, iLevel);
        set(17, iQuantity);
        set(18, iRight);
        set(19, iSequence);
        set(20, rltBillType);
        set(21, rltValid);
        set(22, rParentId);
        set(23, rPaybillId);
        set(24, rPaytermId);
        set(25, isFinished);
        set(26, isIncome);
        set(27, isActive);
        set(28, zSigma);
        set(29, zLanguage);
        set(30, zCreateBy);
        set(31, zCreateTime);
        set(32, zUpdateBy);
        set(33, zUpdateTime);
    }
}
