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
import up.god.jooq.tables.records.PsiUbicationRecord;


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
public class PsiUbication extends TableImpl<PsiUbicationRecord> {

    private static final long serialVersionUID = -1732070321;

    /**
     * The reference instance of <code>DB_HTL.PSI_UBICATION</code>
     */
    public static final PsiUbication PSI_UBICATION = new PsiUbication();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PsiUbicationRecord> getRecordType() {
        return PsiUbicationRecord.class;
    }

    /**
     * The column <code>DB_HTL.PSI_UBICATION.PK_ID</code>. uniqueId,PK_ID
     */
    public final TableField<PsiUbicationRecord, Long> PK_ID = createField("PK_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.T_COMMENT</code>. comment,T_COMMENT
     */
    public final TableField<PsiUbicationRecord, String> T_COMMENT = createField("T_COMMENT", org.jooq.impl.SQLDataType.CLOB, this, "comment,T_COMMENT");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.V_OWNER_ID</code>. ownerId,V_OWNER_ID
     */
    public final TableField<PsiUbicationRecord, String> V_OWNER_ID = createField("V_OWNER_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "ownerId,V_OWNER_ID");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.S_NAME</code>. name,S_NAME
     */
    public final TableField<PsiUbicationRecord, String> S_NAME = createField("S_NAME", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "name,S_NAME");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.S_CODE</code>. code,S_CODE
     */
    public final TableField<PsiUbicationRecord, String> S_CODE = createField("S_CODE", org.jooq.impl.SQLDataType.VARCHAR(36), this, "code,S_CODE");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.S_BARCODE</code>. barCode,S_BARCODE
     */
    public final TableField<PsiUbicationRecord, String> S_BARCODE = createField("S_BARCODE", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "barCode,S_BARCODE");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.S_FULLNAME</code>. fullName,S_FULLNAME
     */
    public final TableField<PsiUbicationRecord, String> S_FULLNAME = createField("S_FULLNAME", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "fullName,S_FULLNAME");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.J_CONFIG</code>. config,J_CONFIG
     */
    public final TableField<PsiUbicationRecord, String> J_CONFIG = createField("J_CONFIG", org.jooq.impl.SQLDataType.CLOB, this, "config,J_CONFIG");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.I_X_CHANNEL</code>. xChannel,I_X_CHANNEL
     */
    public final TableField<PsiUbicationRecord, Integer> I_X_CHANNEL = createField("I_X_CHANNEL", org.jooq.impl.SQLDataType.INTEGER, this, "xChannel,I_X_CHANNEL");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.I_Y_PACK</code>. yPack,I_Y_PACK
     */
    public final TableField<PsiUbicationRecord, Integer> I_Y_PACK = createField("I_Y_PACK", org.jooq.impl.SQLDataType.INTEGER, this, "yPack,I_Y_PACK");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.I_Z_HEIGHT</code>. zHeight,I_Z_HEIGHT
     */
    public final TableField<PsiUbicationRecord, Integer> I_Z_HEIGHT = createField("I_Z_HEIGHT", org.jooq.impl.SQLDataType.INTEGER, this, "zHeight,I_Z_HEIGHT");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.H_CAT_ID</code>. category,H_CAT_ID
     */
    public final TableField<PsiUbicationRecord, String> H_CAT_ID = createField("H_CAT_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "category,H_CAT_ID");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.R_WH_ID</code>. warehouseId,R_WH_ID
     */
    public final TableField<PsiUbicationRecord, Long> R_WH_ID = createField("R_WH_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "warehouseId,R_WH_ID");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.IS_REJECT</code>. reject,IS_REJECT
     */
    public final TableField<PsiUbicationRecord, Boolean> IS_REJECT = createField("IS_REJECT", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "reject,IS_REJECT");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.IS_SCRAP</code>. scrap,IS_SCRAP
     */
    public final TableField<PsiUbicationRecord, Boolean> IS_SCRAP = createField("IS_SCRAP", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "scrap,IS_SCRAP");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.IS_ACTIVE</code>. active,IS_ACTIVE
     */
    public final TableField<PsiUbicationRecord, Boolean> IS_ACTIVE = createField("IS_ACTIVE", org.jooq.impl.SQLDataType.BOOLEAN, this, "active,IS_ACTIVE");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_SIGMA</code>. sigma,Z_SIGMA
     */
    public final TableField<PsiUbicationRecord, String> Z_SIGMA = createField("Z_SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "sigma,Z_SIGMA");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public final TableField<PsiUbicationRecord, String> Z_LANGUAGE = createField("Z_LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "language,Z_LANGUAGE");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_CREATE_BY</code>. createBy,Z_CREATE_BY
     */
    public final TableField<PsiUbicationRecord, String> Z_CREATE_BY = createField("Z_CREATE_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "createBy,Z_CREATE_BY");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_CREATE_TIME</code>. createTime,Z_CREATE_TIME
     */
    public final TableField<PsiUbicationRecord, LocalDateTime> Z_CREATE_TIME = createField("Z_CREATE_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "createTime,Z_CREATE_TIME");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_UPDATE_BY</code>. updateBy,Z_UPDATE_BY
     */
    public final TableField<PsiUbicationRecord, String> Z_UPDATE_BY = createField("Z_UPDATE_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "updateBy,Z_UPDATE_BY");

    /**
     * The column <code>DB_HTL.PSI_UBICATION.Z_UPDATE_TIME</code>. updateTime,Z_UPDATE_TIME
     */
    public final TableField<PsiUbicationRecord, LocalDateTime> Z_UPDATE_TIME = createField("Z_UPDATE_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "updateTime,Z_UPDATE_TIME");

    /**
     * Create a <code>DB_HTL.PSI_UBICATION</code> table reference
     */
    public PsiUbication() {
        this(DSL.name("PSI_UBICATION"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.PSI_UBICATION</code> table reference
     */
    public PsiUbication(String alias) {
        this(DSL.name(alias), PSI_UBICATION);
    }

    /**
     * Create an aliased <code>DB_HTL.PSI_UBICATION</code> table reference
     */
    public PsiUbication(Name alias) {
        this(alias, PSI_UBICATION);
    }

    private PsiUbication(Name alias, Table<PsiUbicationRecord> aliased) {
        this(alias, aliased, null);
    }

    private PsiUbication(Name alias, Table<PsiUbicationRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.PSI_UBICATION_FK_PSI_UBICATION_H_CAT_ID, Indexes.PSI_UBICATION_FK_PSI_UBICATION_R_WH_ID, Indexes.PSI_UBICATION_PRIMARY, Indexes.PSI_UBICATION_UK_PSI_UBICATION_S_CODE_Z_SIGMA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PsiUbicationRecord, Long> getIdentity() {
        return Keys.IDENTITY_PSI_UBICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PsiUbicationRecord> getPrimaryKey() {
        return Keys.KEY_PSI_UBICATION_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PsiUbicationRecord>> getKeys() {
        return Arrays.<UniqueKey<PsiUbicationRecord>>asList(Keys.KEY_PSI_UBICATION_PRIMARY, Keys.KEY_PSI_UBICATION_UK_PSI_UBICATION_S_CODE_Z_SIGMA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PsiUbication as(String alias) {
        return new PsiUbication(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PsiUbication as(Name alias) {
        return new PsiUbication(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PsiUbication rename(String name) {
        return new PsiUbication(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PsiUbication rename(Name name) {
        return new PsiUbication(name, null);
    }
}
