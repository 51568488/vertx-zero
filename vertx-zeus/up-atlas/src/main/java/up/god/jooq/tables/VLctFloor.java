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
import up.god.jooq.tables.records.VLctFloorRecord;


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
public class VLctFloor extends TableImpl<VLctFloorRecord> {

    private static final long serialVersionUID = 2064001218;

    /**
     * The reference instance of <code>DB_HTL.v_lct_floor</code>
     */
    public static final VLctFloor V_LCT_FLOOR = new VLctFloor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VLctFloorRecord> getRecordType() {
        return VLctFloorRecord.class;
    }

    /**
     * The column <code>DB_HTL.v_lct_floor.PK_ID</code>. uniqueId,PK_ID
     */
    public final TableField<VLctFloorRecord, Long> PK_ID = createField("PK_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.v_lct_floor.S_NAME</code>. name,S_NAME
     */
    public final TableField<VLctFloorRecord, String> S_NAME = createField("S_NAME", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.v_lct_floor.S_CODE</code>. code,S_CODE
     */
    public final TableField<VLctFloorRecord, String> S_CODE = createField("S_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * The column <code>DB_HTL.v_lct_floor.I_ORDER</code>. order,I_ORDER
     */
    public final TableField<VLctFloorRecord, Integer> I_ORDER = createField("I_ORDER", org.jooq.impl.SQLDataType.INTEGER, this, "order,I_ORDER");

    /**
     * The column <code>DB_HTL.v_lct_floor.R_TENT_ID</code>. tentId,R_TENT_ID
     */
    public final TableField<VLctFloorRecord, Long> R_TENT_ID = createField("R_TENT_ID", org.jooq.impl.SQLDataType.BIGINT, this, "tentId,R_TENT_ID");

    /**
     * The column <code>DB_HTL.v_lct_floor.S_TENT_NAME</code>. name,S_NAME
     */
    public final TableField<VLctFloorRecord, String> S_TENT_NAME = createField("S_TENT_NAME", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.v_lct_floor.S_TENT_CODE</code>. code,S_CODE
     */
    public final TableField<VLctFloorRecord, String> S_TENT_CODE = createField("S_TENT_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * The column <code>DB_HTL.v_lct_floor.R_LOCATION_ID</code>. locationId,R_LOCATION_ID
     */
    public final TableField<VLctFloorRecord, Long> R_LOCATION_ID = createField("R_LOCATION_ID", org.jooq.impl.SQLDataType.BIGINT, this, "locationId,R_LOCATION_ID");

    /**
     * Create a <code>DB_HTL.v_lct_floor</code> table reference
     */
    public VLctFloor() {
        this(DSL.name("v_lct_floor"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.v_lct_floor</code> table reference
     */
    public VLctFloor(String alias) {
        this(DSL.name(alias), V_LCT_FLOOR);
    }

    /**
     * Create an aliased <code>DB_HTL.v_lct_floor</code> table reference
     */
    public VLctFloor(Name alias) {
        this(alias, V_LCT_FLOOR);
    }

    private VLctFloor(Name alias, Table<VLctFloorRecord> aliased) {
        this(alias, aliased, null);
    }

    private VLctFloor(Name alias, Table<VLctFloorRecord> aliased, Field<?>[] parameters) {
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
    public VLctFloor as(String alias) {
        return new VLctFloor(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VLctFloor as(Name alias) {
        return new VLctFloor(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VLctFloor rename(String name) {
        return new VLctFloor(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VLctFloor rename(Name name) {
        return new VLctFloor(name, null);
    }
}
