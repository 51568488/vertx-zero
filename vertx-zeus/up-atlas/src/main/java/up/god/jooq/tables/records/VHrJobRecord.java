/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.TableRecordImpl;

import up.god.jooq.tables.VHrJob;
import up.god.jooq.tables.interfaces.IVHrJob;


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
public class VHrJobRecord extends TableRecordImpl<VHrJobRecord> implements Record13<Long, String, String, Boolean, Long, String, Long, String, String, String, Integer, Integer, Integer>, IVHrJob {

    private static final long serialVersionUID = -1443802672;

    /**
     * Setter for <code>DB_HTL.v_hr_job.J_PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public VHrJobRecord setJPkId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.J_PK_ID</code>. uniqueId,PK_ID
     */
    @Override
    public Long getJPkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.J_S_NAME</code>. name,S_NAME
     */
    @Override
    public VHrJobRecord setJSName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.J_S_NAME</code>. name,S_NAME
     */
    @Override
    public String getJSName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.S_STATUS</code>. status,S_STATUS
     */
    @Override
    public VHrJobRecord setSStatus(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.S_STATUS</code>. status,S_STATUS
     */
    @Override
    public String getSStatus() {
        return (String) get(2);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public VHrJobRecord setIsActive(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    @Override
    public Boolean getIsActive() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.R_DEPT_ID</code>. departmentId,R_DEPT_ID
     */
    @Override
    public VHrJobRecord setRDeptId(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.R_DEPT_ID</code>. departmentId,R_DEPT_ID
     */
    @Override
    public Long getRDeptId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.D_S_NAME</code>. 部门名称,name,S_NAME
     */
    @Override
    public VHrJobRecord setDSName(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.D_S_NAME</code>. 部门名称,name,S_NAME
     */
    @Override
    public String getDSName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.R_COMPANY_ID</code>. companyId,R_COMPANY_ID
     */
    @Override
    public VHrJobRecord setRCompanyId(Long value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.R_COMPANY_ID</code>. companyId,R_COMPANY_ID
     */
    @Override
    public Long getRCompanyId() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.C_S_NAME</code>. name,S_NAME
     */
    @Override
    public VHrJobRecord setCSName(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.C_S_NAME</code>. name,S_NAME
     */
    @Override
    public String getCSName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.T_COMMENT</code>. comment,T_COMMENT
     */
    @Override
    public VHrJobRecord setTComment(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.T_COMMENT</code>. comment,T_COMMENT
     */
    @Override
    public String getTComment() {
        return (String) get(8);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.T_REQUIREMENTS</code>. requirements,T_REQUIREMENTS
     */
    @Override
    public VHrJobRecord setTRequirements(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.T_REQUIREMENTS</code>. requirements,T_REQUIREMENTS
     */
    @Override
    public String getTRequirements() {
        return (String) get(9);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.I_NO_RECRUIT</code>. 招聘数量,recruitNumber,I_NO_RECRUIT
     */
    @Override
    public VHrJobRecord setINoRecruit(Integer value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.I_NO_RECRUIT</code>. 招聘数量,recruitNumber,I_NO_RECRUIT
     */
    @Override
    public Integer getINoRecruit() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.I_NO_HIRED</code>. 已招数量,hiredNumber,I_NO_HIRED
     */
    @Override
    public VHrJobRecord setINoHired(Integer value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.I_NO_HIRED</code>. 已招数量,hiredNumber,I_NO_HIRED
     */
    @Override
    public Integer getINoHired() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>DB_HTL.v_hr_job.I_NO_EMPLOYEE</code>. 目前人数,employeeNumber,I_NO_EMPLOYEE
     */
    @Override
    public VHrJobRecord setINoEmployee(Integer value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>DB_HTL.v_hr_job.I_NO_EMPLOYEE</code>. 目前人数,employeeNumber,I_NO_EMPLOYEE
     */
    @Override
    public Integer getINoEmployee() {
        return (Integer) get(12);
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row13<Long, String, String, Boolean, Long, String, Long, String, String, String, Integer, Integer, Integer> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row13<Long, String, String, Boolean, Long, String, Long, String, String, String, Integer, Integer, Integer> valuesRow() {
        return (Row13) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return VHrJob.V_HR_JOB.J_PK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return VHrJob.V_HR_JOB.J_S_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return VHrJob.V_HR_JOB.S_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return VHrJob.V_HR_JOB.IS_ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return VHrJob.V_HR_JOB.R_DEPT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return VHrJob.V_HR_JOB.D_S_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return VHrJob.V_HR_JOB.R_COMPANY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return VHrJob.V_HR_JOB.C_S_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return VHrJob.V_HR_JOB.T_COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return VHrJob.V_HR_JOB.T_REQUIREMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field11() {
        return VHrJob.V_HR_JOB.I_NO_RECRUIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field12() {
        return VHrJob.V_HR_JOB.I_NO_HIRED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field13() {
        return VHrJob.V_HR_JOB.I_NO_EMPLOYEE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getJPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getJSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getSStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component4() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component5() {
        return getRDeptId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getDSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getRCompanyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getCSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getTComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getTRequirements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component11() {
        return getINoRecruit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component12() {
        return getINoHired();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component13() {
        return getINoEmployee();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getJPkId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getJSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value4() {
        return getIsActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getRDeptId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getDSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getRCompanyId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getCSName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getTComment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getTRequirements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value11() {
        return getINoRecruit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value12() {
        return getINoHired();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value13() {
        return getINoEmployee();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value1(Long value) {
        setJPkId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value2(String value) {
        setJSName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value3(String value) {
        setSStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value4(Boolean value) {
        setIsActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value5(Long value) {
        setRDeptId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value6(String value) {
        setDSName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value7(Long value) {
        setRCompanyId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value8(String value) {
        setCSName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value9(String value) {
        setTComment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value10(String value) {
        setTRequirements(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value11(Integer value) {
        setINoRecruit(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value12(Integer value) {
        setINoHired(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord value13(Integer value) {
        setINoEmployee(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHrJobRecord values(Long value1, String value2, String value3, Boolean value4, Long value5, String value6, Long value7, String value8, String value9, String value10, Integer value11, Integer value12, Integer value13) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IVHrJob from) {
        setJPkId(from.getJPkId());
        setJSName(from.getJSName());
        setSStatus(from.getSStatus());
        setIsActive(from.getIsActive());
        setRDeptId(from.getRDeptId());
        setDSName(from.getDSName());
        setRCompanyId(from.getRCompanyId());
        setCSName(from.getCSName());
        setTComment(from.getTComment());
        setTRequirements(from.getTRequirements());
        setINoRecruit(from.getINoRecruit());
        setINoHired(from.getINoHired());
        setINoEmployee(from.getINoEmployee());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IVHrJob> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VHrJobRecord
     */
    public VHrJobRecord() {
        super(VHrJob.V_HR_JOB);
    }

    /**
     * Create a detached, initialised VHrJobRecord
     */
    public VHrJobRecord(Long jPkId, String jSName, String sStatus, Boolean isActive, Long rDeptId, String dSName, Long rCompanyId, String cSName, String tComment, String tRequirements, Integer iNoRecruit, Integer iNoHired, Integer iNoEmployee) {
        super(VHrJob.V_HR_JOB);

        set(0, jPkId);
        set(1, jSName);
        set(2, sStatus);
        set(3, isActive);
        set(4, rDeptId);
        set(5, dSName);
        set(6, rCompanyId);
        set(7, cSName);
        set(8, tComment);
        set(9, tRequirements);
        set(10, iNoRecruit);
        set(11, iNoHired);
        set(12, iNoEmployee);
    }
}
