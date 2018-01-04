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
import up.god.jooq.tables.records.VSecGroupRolesRecord;


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
public class VSecGroupRoles extends TableImpl<VSecGroupRolesRecord> {

    private static final long serialVersionUID = -2125397578;

    /**
     * The reference instance of <code>DB_HTL.v_sec_group_roles</code>
     */
    public static final VSecGroupRoles V_SEC_GROUP_ROLES = new VSecGroupRoles();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VSecGroupRolesRecord> getRecordType() {
        return VSecGroupRolesRecord.class;
    }

    /**
     * The column <code>DB_HTL.v_sec_group_roles.R_ID</code>. uniqueId,PK_ID
     */
    public final TableField<VSecGroupRolesRecord, String> R_ID = createField("R_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.v_sec_group_roles.R_NAME</code>. 角色名称,name,S_NAME
     */
    public final TableField<VSecGroupRolesRecord, String> R_NAME = createField("R_NAME", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "角色名称,name,S_NAME");

    /**
     * The column <code>DB_HTL.v_sec_group_roles.R_CODE</code>. code,S_CODE
     */
    public final TableField<VSecGroupRolesRecord, String> R_CODE = createField("R_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * The column <code>DB_HTL.v_sec_group_roles.G_ID</code>. uniqueId,PK_ID
     */
    public final TableField<VSecGroupRolesRecord, String> G_ID = createField("G_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.v_sec_group_roles.G_NAME</code>. name,S_NAME
     */
    public final TableField<VSecGroupRolesRecord, String> G_NAME = createField("G_NAME", org.jooq.impl.SQLDataType.VARCHAR(64), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.v_sec_group_roles.G_CODE</code>. code,S_CODE
     */
    public final TableField<VSecGroupRolesRecord, String> G_CODE = createField("G_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * Create a <code>DB_HTL.v_sec_group_roles</code> table reference
     */
    public VSecGroupRoles() {
        this(DSL.name("v_sec_group_roles"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.v_sec_group_roles</code> table reference
     */
    public VSecGroupRoles(String alias) {
        this(DSL.name(alias), V_SEC_GROUP_ROLES);
    }

    /**
     * Create an aliased <code>DB_HTL.v_sec_group_roles</code> table reference
     */
    public VSecGroupRoles(Name alias) {
        this(alias, V_SEC_GROUP_ROLES);
    }

    private VSecGroupRoles(Name alias, Table<VSecGroupRolesRecord> aliased) {
        this(alias, aliased, null);
    }

    private VSecGroupRoles(Name alias, Table<VSecGroupRolesRecord> aliased, Field<?>[] parameters) {
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
    public VSecGroupRoles as(String alias) {
        return new VSecGroupRoles(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VSecGroupRoles as(Name alias) {
        return new VSecGroupRoles(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VSecGroupRoles rename(String name) {
        return new VSecGroupRoles(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VSecGroupRoles rename(Name name) {
        return new VSecGroupRoles(name, null);
    }
}
