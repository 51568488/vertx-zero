/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


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
public interface IVSecUserRoles extends Serializable {

    /**
     * Setter for <code>DB_HTL.v_sec_user_roles.R_USER_ID</code>. userId,R_USER_ID
     */
    public IVSecUserRoles setRUserId(String value);

    /**
     * Getter for <code>DB_HTL.v_sec_user_roles.R_USER_ID</code>. userId,R_USER_ID
     */
    public String getRUserId();

    /**
     * Setter for <code>DB_HTL.v_sec_user_roles.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    public IVSecUserRoles setSUsername(String value);

    /**
     * Getter for <code>DB_HTL.v_sec_user_roles.S_USERNAME</code>. 登陆账号,username,S_USERNAME
     */
    public String getSUsername();

    /**
     * Setter for <code>DB_HTL.v_sec_user_roles.R_ROLE_ID</code>. roleId,R_ROLE_ID
     */
    public IVSecUserRoles setRRoleId(String value);

    /**
     * Getter for <code>DB_HTL.v_sec_user_roles.R_ROLE_ID</code>. roleId,R_ROLE_ID
     */
    public String getRRoleId();

    /**
     * Setter for <code>DB_HTL.v_sec_user_roles.S_NAME</code>. 角色名称,name,S_NAME
     */
    public IVSecUserRoles setSName(String value);

    /**
     * Getter for <code>DB_HTL.v_sec_user_roles.S_NAME</code>. 角色名称,name,S_NAME
     */
    public String getSName();

    /**
     * Setter for <code>DB_HTL.v_sec_user_roles.S_CODE</code>. code,S_CODE
     */
    public IVSecUserRoles setSCode(String value);

    /**
     * Getter for <code>DB_HTL.v_sec_user_roles.S_CODE</code>. code,S_CODE
     */
    public String getSCode();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IVSecUserRoles
     */
    public void from(up.god.jooq.tables.interfaces.IVSecUserRoles from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IVSecUserRoles
     */
    public <E extends up.god.jooq.tables.interfaces.IVSecUserRoles> E into(E into);

    default IVSecUserRoles fromJson(io.vertx.core.json.JsonObject json) {
        setRUserId(json.getString("R_USER_ID"));
        setSUsername(json.getString("S_USERNAME"));
        setRRoleId(json.getString("R_ROLE_ID"));
        setSName(json.getString("S_NAME"));
        setSCode(json.getString("S_CODE"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("R_USER_ID",getRUserId());
        json.put("S_USERNAME",getSUsername());
        json.put("R_ROLE_ID",getRRoleId());
        json.put("S_NAME",getSName());
        json.put("S_CODE",getSCode());
        return json;
    }

}
