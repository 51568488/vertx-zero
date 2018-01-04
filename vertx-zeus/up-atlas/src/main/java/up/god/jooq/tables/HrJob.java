/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import up.god.jooq.DbHtl;
import up.god.jooq.Indexes;
import up.god.jooq.Keys;
import up.god.jooq.tables.records.HrJobRecord;


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
public class HrJob extends TableImpl<HrJobRecord> {

    private static final long serialVersionUID = 782224874;

    /**
     * The reference instance of <code>DB_HTL.HR_JOB</code>
     */
    public static final HrJob HR_JOB = new HrJob();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HrJobRecord> getRecordType() {
        return HrJobRecord.class;
    }

    /**
     * The column <code>DB_HTL.HR_JOB.PK_ID</code>. uniqueId,PK_ID
     */
    public final TableField<HrJobRecord, Long> PK_ID = createField("PK_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.HR_JOB.T_COMMENT</code>. comment,T_COMMENT
     */
    public final TableField<HrJobRecord, String> T_COMMENT = createField("T_COMMENT", org.jooq.impl.SQLDataType.CLOB, this, "comment,T_COMMENT");

    /**
     * The column <code>DB_HTL.HR_JOB.T_REQUIREMENTS</code>. requirements,T_REQUIREMENTS
     */
    public final TableField<HrJobRecord, String> T_REQUIREMENTS = createField("T_REQUIREMENTS", org.jooq.impl.SQLDataType.CLOB, this, "requirements,T_REQUIREMENTS");

    /**
     * The column <code>DB_HTL.HR_JOB.S_NAME</code>. name,S_NAME
     */
    public final TableField<HrJobRecord, String> S_NAME = createField("S_NAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.HR_JOB.S_CODE</code>. code,S_CODE
     */
    public final TableField<HrJobRecord, String> S_CODE = createField("S_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * The column <code>DB_HTL.HR_JOB.S_STATUS</code>. status,S_STATUS
     */
    public final TableField<HrJobRecord, String> S_STATUS = createField("S_STATUS", org.jooq.impl.SQLDataType.VARCHAR(16).nullable(false), this, "status,S_STATUS");

    /**
     * The column <code>DB_HTL.HR_JOB.J_CONFIG</code>. config,J_CONFIG
     */
    public final TableField<HrJobRecord, String> J_CONFIG = createField("J_CONFIG", org.jooq.impl.SQLDataType.CLOB, this, "config,J_CONFIG");

    /**
     * The column <code>DB_HTL.HR_JOB.I_NO_EMPLOYEE</code>. 目前人数,employeeNumber,I_NO_EMPLOYEE
     */
    public final TableField<HrJobRecord, Integer> I_NO_EMPLOYEE = createField("I_NO_EMPLOYEE", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "目前人数,employeeNumber,I_NO_EMPLOYEE");

    /**
     * The column <code>DB_HTL.HR_JOB.I_NO_HIRED</code>. 已招数量,hiredNumber,I_NO_HIRED
     */
    public final TableField<HrJobRecord, Integer> I_NO_HIRED = createField("I_NO_HIRED", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "已招数量,hiredNumber,I_NO_HIRED");

    /**
     * The column <code>DB_HTL.HR_JOB.I_NO_RECRUIT</code>. 招聘数量,recruitNumber,I_NO_RECRUIT
     */
    public final TableField<HrJobRecord, Integer> I_NO_RECRUIT = createField("I_NO_RECRUIT", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "招聘数量,recruitNumber,I_NO_RECRUIT");

    /**
     * The column <code>DB_HTL.HR_JOB.R_COMPANY_ID</code>. companyId,R_COMPANY_ID
     */
    public final TableField<HrJobRecord, Long> R_COMPANY_ID = createField("R_COMPANY_ID", org.jooq.impl.SQLDataType.BIGINT, this, "companyId,R_COMPANY_ID");

    /**
     * The column <code>DB_HTL.HR_JOB.R_DEPT_ID</code>. departmentId,R_DEPT_ID
     */
    public final TableField<HrJobRecord, Long> R_DEPT_ID = createField("R_DEPT_ID", org.jooq.impl.SQLDataType.BIGINT, this, "departmentId,R_DEPT_ID");

    /**
     * The column <code>DB_HTL.HR_JOB.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public final TableField<HrJobRecord, Boolean> IS_ACTIVE = createField("IS_ACTIVE", org.jooq.impl.SQLDataType.BOOLEAN, this, "active,IS_ACTIVE");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public final TableField<HrJobRecord, String> Z_SIGMA = createField("Z_SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "sigma,Z_SIGMA");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public final TableField<HrJobRecord, String> Z_LANGUAGE = createField("Z_LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "language,Z_LANGUAGE");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public final TableField<HrJobRecord, String> Z_CREATE_BY = createField("Z_CREATE_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "createBy,Z_CREATE_BY");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public final TableField<HrJobRecord, LocalDateTime> Z_CREATE_TIME = createField("Z_CREATE_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "createTime,Z_CREATE_TIME");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public final TableField<HrJobRecord, String> Z_UPDATE_BY = createField("Z_UPDATE_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "updateBy,Z_UPDATE_BY");

    /**
     * The column <code>DB_HTL.HR_JOB.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public final TableField<HrJobRecord, LocalDateTime> Z_UPDATE_TIME = createField("Z_UPDATE_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "updateTime,Z_UPDATE_TIME");

    /**
     * Create a <code>DB_HTL.HR_JOB</code> table reference
     */
    public HrJob() {
        this(DSL.name("HR_JOB"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.HR_JOB</code> table reference
     */
    public HrJob(String alias) {
        this(DSL.name(alias), HR_JOB);
    }

    /**
     * Create an aliased <code>DB_HTL.HR_JOB</code> table reference
     */
    public HrJob(Name alias) {
        this(alias, HR_JOB);
    }

    private HrJob(Name alias, Table<HrJobRecord> aliased) {
        this(alias, aliased, null);
    }

    private HrJob(Name alias, Table<HrJobRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DbHtl.DB_HTL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.HR_JOB_FK_HR_JOB_R_COMPANY_ID, Indexes.HR_JOB_FK_HR_JOB_R_DEPT_ID, Indexes.HR_JOB_PRIMARY, Indexes.HR_JOB_UK_HR_JOB_S_CODE_Z_SIGMA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<HrJobRecord, Long> getIdentity() {
        return Keys.IDENTITY_HR_JOB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HrJobRecord> getPrimaryKey() {
        return Keys.KEY_HR_JOB_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HrJobRecord>> getKeys() {
        return Arrays.<UniqueKey<HrJobRecord>>asList(Keys.KEY_HR_JOB_PRIMARY, Keys.KEY_HR_JOB_UK_HR_JOB_S_CODE_Z_SIGMA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HrJob as(String alias) {
        return new HrJob(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HrJob as(Name alias) {
        return new HrJob(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HrJob rename(String name) {
        return new HrJob(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HrJob rename(Name name) {
        return new HrJob(name, null);
    }
}
