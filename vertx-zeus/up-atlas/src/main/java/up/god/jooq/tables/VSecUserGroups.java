/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import up.god.jooq.DbHtl;
import up.god.jooq.tables.records.VSecUserGroupsRecord;


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
public class VSecUserGroups extends TableImpl<VSecUserGroupsRecord> {

    private static final long serialVersionUID = 1103388880;

    /**
     * The reference instance of <code>DB_HTL.v_sec_user_groups</code>
     */
    public static final VSecUserGroups V_SEC_USER_GROUPS = new VSecUserGroups();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VSecUserGroupsRecord> getRecordType() {
        return VSecUserGroupsRecord.class;
    }

    /**
     * The column <code>DB_HTL.v_sec_user_groups.R_USER_ID</code>. userId,R_USER_ID
     */
    public final TableField<VSecUserGroupsRecord, String> R_USER_ID = createField("R_USER_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "userId,R_USER_ID");

    /**
     * The column <code>DB_HTL.v_sec_user_groups.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    public final TableField<VSecUserGroupsRecord, String> S_USERNAME = createField("S_USERNAME", org.jooq.impl.SQLDataType.VARCHAR(32), this, "登陆账号,username,S_USERNAME");

    /**
     * The column <code>DB_HTL.v_sec_user_groups.R_GROUP_ID</code>. groupId,R_GROUP_ID
     */
    public final TableField<VSecUserGroupsRecord, String> R_GROUP_ID = createField("R_GROUP_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "groupId,R_GROUP_ID");

    /**
     * The column <code>DB_HTL.v_sec_user_groups.S_NAME</code>. name,S_NAME
     */
    public final TableField<VSecUserGroupsRecord, String> S_NAME = createField("S_NAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.v_sec_user_groups.S_CODE</code>. code,S_CODE
     */
    public final TableField<VSecUserGroupsRecord, String> S_CODE = createField("S_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * Create a <code>DB_HTL.v_sec_user_groups</code> table reference
     */
    public VSecUserGroups() {
        this(DSL.name("v_sec_user_groups"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.v_sec_user_groups</code> table reference
     */
    public VSecUserGroups(String alias) {
        this(DSL.name(alias), V_SEC_USER_GROUPS);
    }

    /**
     * Create an aliased <code>DB_HTL.v_sec_user_groups</code> table reference
     */
    public VSecUserGroups(Name alias) {
        this(alias, V_SEC_USER_GROUPS);
    }

    private VSecUserGroups(Name alias, Table<VSecUserGroupsRecord> aliased) {
        this(alias, aliased, null);
    }

    private VSecUserGroups(Name alias, Table<VSecUserGroupsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "VIEW");
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
    public VSecUserGroups as(String alias) {
        return new VSecUserGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSecUserGroups as(Name alias) {
        return new VSecUserGroups(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VSecUserGroups rename(String name) {
        return new VSecUserGroups(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VSecUserGroups rename(Name name) {
        return new VSecUserGroups(name, null);
    }
}
