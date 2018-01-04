/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
import up.god.jooq.tables.records.SecRelGroupRoleRecord;


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
public class SecRelGroupRole extends TableImpl<SecRelGroupRoleRecord> {

    private static final long serialVersionUID = -1583442887;

    /**
     * The reference instance of <code>DB_HTL.SEC_REL_GROUP_ROLE</code>
     */
    public static final SecRelGroupRole SEC_REL_GROUP_ROLE = new SecRelGroupRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecRelGroupRoleRecord> getRecordType() {
        return SecRelGroupRoleRecord.class;
    }

    /**
     * The column <code>DB_HTL.SEC_REL_GROUP_ROLE.R_ROLE_ID</code>. roleId,R_ROLE_ID
     */
    public final TableField<SecRelGroupRoleRecord, String> R_ROLE_ID = createField("R_ROLE_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "roleId,R_ROLE_ID");

    /**
     * The column <code>DB_HTL.SEC_REL_GROUP_ROLE.R_GROUP_ID</code>. groupId,R_GROUP_ID
     */
    public final TableField<SecRelGroupRoleRecord, String> R_GROUP_ID = createField("R_GROUP_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "groupId,R_GROUP_ID");

    /**
     * Create a <code>DB_HTL.SEC_REL_GROUP_ROLE</code> table reference
     */
    public SecRelGroupRole() {
        this(DSL.name("SEC_REL_GROUP_ROLE"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.SEC_REL_GROUP_ROLE</code> table reference
     */
    public SecRelGroupRole(String alias) {
        this(DSL.name(alias), SEC_REL_GROUP_ROLE);
    }

    /**
     * Create an aliased <code>DB_HTL.SEC_REL_GROUP_ROLE</code> table reference
     */
    public SecRelGroupRole(Name alias) {
        this(alias, SEC_REL_GROUP_ROLE);
    }

    private SecRelGroupRole(Name alias, Table<SecRelGroupRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private SecRelGroupRole(Name alias, Table<SecRelGroupRoleRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.SEC_REL_GROUP_ROLE_FK_SEC_REL_GR_ROLE, Indexes.SEC_REL_GROUP_ROLE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SecRelGroupRoleRecord> getPrimaryKey() {
        return Keys.KEY_SEC_REL_GROUP_ROLE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SecRelGroupRoleRecord>> getKeys() {
        return Arrays.<UniqueKey<SecRelGroupRoleRecord>>asList(Keys.KEY_SEC_REL_GROUP_ROLE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecRelGroupRole as(String alias) {
        return new SecRelGroupRole(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecRelGroupRole as(Name alias) {
        return new SecRelGroupRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecRelGroupRole rename(String name) {
        return new SecRelGroupRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecRelGroupRole rename(Name name) {
        return new SecRelGroupRole(name, null);
    }
}
