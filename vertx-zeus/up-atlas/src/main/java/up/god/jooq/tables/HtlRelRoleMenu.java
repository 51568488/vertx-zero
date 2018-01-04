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
import up.god.jooq.tables.records.HtlRelRoleMenuRecord;


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
public class HtlRelRoleMenu extends TableImpl<HtlRelRoleMenuRecord> {

    private static final long serialVersionUID = -1886051398;

    /**
     * The reference instance of <code>DB_HTL.HTL_REL_ROLE_MENU</code>
     */
    public static final HtlRelRoleMenu HTL_REL_ROLE_MENU = new HtlRelRoleMenu();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HtlRelRoleMenuRecord> getRecordType() {
        return HtlRelRoleMenuRecord.class;
    }

    /**
     * The column <code>DB_HTL.HTL_REL_ROLE_MENU.R_ROLE_ID</code>. roleId,R_ROLE_ID
     */
    public final TableField<HtlRelRoleMenuRecord, String> R_ROLE_ID = createField("R_ROLE_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "roleId,R_ROLE_ID");

    /**
     * The column <code>DB_HTL.HTL_REL_ROLE_MENU.R_MENU_ID</code>. menuId,R_MENU_ID
     */
    public final TableField<HtlRelRoleMenuRecord, String> R_MENU_ID = createField("R_MENU_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "menuId,R_MENU_ID");

    /**
     * The column <code>DB_HTL.HTL_REL_ROLE_MENU.IS_ORIGINAL</code>. original,IS_ORIGINAL
     */
    public final TableField<HtlRelRoleMenuRecord, Boolean> IS_ORIGINAL = createField("IS_ORIGINAL", org.jooq.impl.SQLDataType.BOOLEAN, this, "original,IS_ORIGINAL");

    /**
     * Create a <code>DB_HTL.HTL_REL_ROLE_MENU</code> table reference
     */
    public HtlRelRoleMenu() {
        this(DSL.name("HTL_REL_ROLE_MENU"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.HTL_REL_ROLE_MENU</code> table reference
     */
    public HtlRelRoleMenu(String alias) {
        this(DSL.name(alias), HTL_REL_ROLE_MENU);
    }

    /**
     * Create an aliased <code>DB_HTL.HTL_REL_ROLE_MENU</code> table reference
     */
    public HtlRelRoleMenu(Name alias) {
        this(alias, HTL_REL_ROLE_MENU);
    }

    private HtlRelRoleMenu(Name alias, Table<HtlRelRoleMenuRecord> aliased) {
        this(alias, aliased, null);
    }

    private HtlRelRoleMenu(Name alias, Table<HtlRelRoleMenuRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.HTL_REL_ROLE_MENU_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HtlRelRoleMenuRecord> getPrimaryKey() {
        return Keys.KEY_HTL_REL_ROLE_MENU_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HtlRelRoleMenuRecord>> getKeys() {
        return Arrays.<UniqueKey<HtlRelRoleMenuRecord>>asList(Keys.KEY_HTL_REL_ROLE_MENU_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlRelRoleMenu as(String alias) {
        return new HtlRelRoleMenu(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HtlRelRoleMenu as(Name alias) {
        return new HtlRelRoleMenu(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HtlRelRoleMenu rename(String name) {
        return new HtlRelRoleMenu(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HtlRelRoleMenu rename(Name name) {
        return new HtlRelRoleMenu(name, null);
    }
}
