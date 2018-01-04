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
import up.god.jooq.tables.records.VHtlEmployeeRecord;


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
public class VHtlEmployee extends TableImpl<VHtlEmployeeRecord> {

    private static final long serialVersionUID = -41992320;

    /**
     * The reference instance of <code>DB_HTL.v_htl_employee</code>
     */
    public static final VHtlEmployee V_HTL_EMPLOYEE = new VHtlEmployee();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VHtlEmployeeRecord> getRecordType() {
        return VHtlEmployeeRecord.class;
    }

    /**
     * The column <code>DB_HTL.v_htl_employee.PK_ID</code>. uniqueId,PK_ID
     */
    public final TableField<VHtlEmployeeRecord, String> PK_ID = createField("PK_ID", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.v_htl_employee.R_HOTEL_ID</code>. hotelId,R_HOTEL_ID
     */
    public final TableField<VHtlEmployeeRecord, Long> R_HOTEL_ID = createField("R_HOTEL_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "hotelId,R_HOTEL_ID");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_REALNAME</code>. realname,S_REALNAME
     */
    public final TableField<VHtlEmployeeRecord, String> S_REALNAME = createField("S_REALNAME", org.jooq.impl.SQLDataType.VARCHAR(64), this, "realname,S_REALNAME");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_NICKNAME</code>. nickname,S_NICKNAME
     */
    public final TableField<VHtlEmployeeRecord, String> S_NICKNAME = createField("S_NICKNAME", org.jooq.impl.SQLDataType.VARCHAR(64), this, "nickname,S_NICKNAME");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    public final TableField<VHtlEmployeeRecord, String> S_USERNAME = createField("S_USERNAME", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "登陆账号,username,S_USERNAME");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_EMAIL</code>. 个人Email,email,S_EMAIL
     */
    public final TableField<VHtlEmployeeRecord, String> S_EMAIL = createField("S_EMAIL", org.jooq.impl.SQLDataType.VARCHAR(72), this, "个人Email,email,S_EMAIL");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_MOBILE</code>. 手机号,mobile,S_MOBILE
     */
    public final TableField<VHtlEmployeeRecord, String> S_MOBILE = createField("S_MOBILE", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "手机号,mobile,S_MOBILE");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_PASSWORD</code>. 登陆口令/密码,password,S_PASSWORD
     */
    public final TableField<VHtlEmployeeRecord, String> S_PASSWORD = createField("S_PASSWORD", org.jooq.impl.SQLDataType.VARCHAR(255), this, "登陆口令/密码,password,S_PASSWORD");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_QQ</code>. 个人QQ号,qq,S_QQ
     */
    public final TableField<VHtlEmployeeRecord, String> S_QQ = createField("S_QQ", org.jooq.impl.SQLDataType.VARCHAR(10), this, "个人QQ号,qq,S_QQ");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_ALIPAY</code>. 支付宝,alipay,S_ALIPAY
     */
    public final TableField<VHtlEmployeeRecord, String> S_ALIPAY = createField("S_ALIPAY", org.jooq.impl.SQLDataType.VARCHAR(72), this, "支付宝,alipay,S_ALIPAY");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_TAOBAO</code>. 淘宝,taobao,S_TAOBAO
     */
    public final TableField<VHtlEmployeeRecord, String> S_TAOBAO = createField("S_TAOBAO", org.jooq.impl.SQLDataType.VARCHAR(72), this, "淘宝,taobao,S_TAOBAO");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_WEIBO</code>. 微博账号,weibo,S_WEIBO
     */
    public final TableField<VHtlEmployeeRecord, String> S_WEIBO = createField("S_WEIBO", org.jooq.impl.SQLDataType.VARCHAR(64), this, "微博账号,weibo,S_WEIBO");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_WECHAT</code>. 微信账号,wechat,S_WECHAT
     */
    public final TableField<VHtlEmployeeRecord, String> S_WECHAT = createField("S_WECHAT", org.jooq.impl.SQLDataType.VARCHAR(64), this, "微信账号,wechat,S_WECHAT");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_EMPLOYEE_ID</code>. uniqueId,PK_ID
     */
    public final TableField<VHtlEmployeeRecord, Long> S_EMPLOYEE_ID = createField("S_EMPLOYEE_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "uniqueId,PK_ID");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_EMPLOYEE_NUM</code>. employeeNumber,S_EMPLOYEE_NUM
     */
    public final TableField<VHtlEmployeeRecord, String> S_EMPLOYEE_NUM = createField("S_EMPLOYEE_NUM", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "employeeNumber,S_EMPLOYEE_NUM");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_GENDER</code>. gender,S_GENDER
     */
    public final TableField<VHtlEmployeeRecord, String> S_GENDER = createField("S_GENDER", org.jooq.impl.SQLDataType.VARCHAR(10), this, "gender,S_GENDER");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_MARITAL</code>. marital,S_MARITAL
     */
    public final TableField<VHtlEmployeeRecord, String> S_MARITAL = createField("S_MARITAL", org.jooq.impl.SQLDataType.VARCHAR(20), this, "marital,S_MARITAL");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_SINID</code>. Social Insurance Number:（加拿大专用）,sinid,S_SINID
     */
    public final TableField<VHtlEmployeeRecord, String> S_SINID = createField("S_SINID", org.jooq.impl.SQLDataType.VARCHAR(64), this, "Social Insurance Number:（加拿大专用）,sinid,S_SINID");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_SSNID</code>. Social Security Number：社会安全码（美国境内用）,ssnid,S_SSNID
     */
    public final TableField<VHtlEmployeeRecord, String> S_SSNID = createField("S_SSNID", org.jooq.impl.SQLDataType.VARCHAR(64), this, "Social Security Number：社会安全码（美国境内用）,ssnid,S_SSNID");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_WORK_EMAIL</code>. workEmail,S_WORK_EMAIL
     */
    public final TableField<VHtlEmployeeRecord, String> S_WORK_EMAIL = createField("S_WORK_EMAIL", org.jooq.impl.SQLDataType.VARCHAR(255), this, "workEmail,S_WORK_EMAIL");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_WORK_LOCATION</code>. workLocation,S_WORK_LOCATION
     */
    public final TableField<VHtlEmployeeRecord, String> S_WORK_LOCATION = createField("S_WORK_LOCATION", org.jooq.impl.SQLDataType.VARCHAR(2048), this, "workLocation,S_WORK_LOCATION");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_WORK_PHONE</code>. workPhone,S_WORK_PHONE
     */
    public final TableField<VHtlEmployeeRecord, String> S_WORK_PHONE = createField("S_WORK_PHONE", org.jooq.impl.SQLDataType.VARCHAR(20), this, "workPhone,S_WORK_PHONE");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_COMPANY</code>. company,S_COMPANY
     */
    public final TableField<VHtlEmployeeRecord, String> S_COMPANY = createField("S_COMPANY", org.jooq.impl.SQLDataType.VARCHAR(64), this, "company,S_COMPANY");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_COUNTRY</code>. country,S_COUNTRY
     */
    public final TableField<VHtlEmployeeRecord, String> S_COUNTRY = createField("S_COUNTRY", org.jooq.impl.SQLDataType.VARCHAR(32), this, "country,S_COUNTRY");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_DEPARTMENT</code>. department,S_DEPARTMENT
     */
    public final TableField<VHtlEmployeeRecord, String> S_DEPARTMENT = createField("S_DEPARTMENT", org.jooq.impl.SQLDataType.VARCHAR(64), this, "department,S_DEPARTMENT");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_SECRET</code>. 专用私钥，OAuth中也可用,secret,S_SECRET
     */
    public final TableField<VHtlEmployeeRecord, String> S_SECRET = createField("S_SECRET", org.jooq.impl.SQLDataType.VARCHAR(255), this, "专用私钥，OAuth中也可用,secret,S_SECRET");

    /**
     * The column <code>DB_HTL.v_htl_employee.T_REDIRECT_URI</code>. redirectUri,T_REDIRECT_URI
     */
    public final TableField<VHtlEmployeeRecord, String> T_REDIRECT_URI = createField("T_REDIRECT_URI", org.jooq.impl.SQLDataType.CLOB, this, "redirectUri,T_REDIRECT_URI");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_SCOPE</code>. scope,S_SCOPE
     */
    public final TableField<VHtlEmployeeRecord, String> S_SCOPE = createField("S_SCOPE", org.jooq.impl.SQLDataType.VARCHAR(64), this, "scope,S_SCOPE");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_CLIENT_SECRET</code>. clientSecret,S_CLIENT_SECRET
     */
    public final TableField<VHtlEmployeeRecord, String> S_CLIENT_SECRET = createField("S_CLIENT_SECRET", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "clientSecret,S_CLIENT_SECRET");

    /**
     * The column <code>DB_HTL.v_htl_employee.S_GRANT_TYPE</code>. grantType,S_GRANT_TYPE
     */
    public final TableField<VHtlEmployeeRecord, String> S_GRANT_TYPE = createField("S_GRANT_TYPE", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "grantType,S_GRANT_TYPE");

    /**
     * The column <code>DB_HTL.v_htl_employee.Z_LANGUAGE</code>. language,Z_LANGUAGE
     */
    public final TableField<VHtlEmployeeRecord, String> Z_LANGUAGE = createField("Z_LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(8), this, "language,Z_LANGUAGE");

    /**
     * Create a <code>DB_HTL.v_htl_employee</code> table reference
     */
    public VHtlEmployee() {
        this(DSL.name("v_htl_employee"), null);
    }

    /**
     * Create an aliased <code>DB_HTL.v_htl_employee</code> table reference
     */
    public VHtlEmployee(String alias) {
        this(DSL.name(alias), V_HTL_EMPLOYEE);
    }

    /**
     * Create an aliased <code>DB_HTL.v_htl_employee</code> table reference
     */
    public VHtlEmployee(Name alias) {
        this(alias, V_HTL_EMPLOYEE);
    }

    private VHtlEmployee(Name alias, Table<VHtlEmployeeRecord> aliased) {
        this(alias, aliased, null);
    }

    private VHtlEmployee(Name alias, Table<VHtlEmployeeRecord> aliased, Field<?>[] parameters) {
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
    public VHtlEmployee as(String alias) {
        return new VHtlEmployee(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VHtlEmployee as(Name alias) {
        return new VHtlEmployee(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VHtlEmployee rename(String name) {
        return new VHtlEmployee(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VHtlEmployee rename(Name name) {
        return new VHtlEmployee(name, null);
    }
}
