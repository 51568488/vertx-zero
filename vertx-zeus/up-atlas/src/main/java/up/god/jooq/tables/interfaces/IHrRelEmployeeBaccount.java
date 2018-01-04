/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


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
public interface IHrRelEmployeeBaccount extends Serializable {

    /**
     * Setter for <code>DB_HTL.HR_REL_EMPLOYEE_BACCOUNT.R_BANKACCOUNT_ID</code>. bankAccountId,R_BANKACCOUNT_ID
     */
    public IHrRelEmployeeBaccount setRBankaccountId(Long value);

    /**
     * Getter for <code>DB_HTL.HR_REL_EMPLOYEE_BACCOUNT.R_BANKACCOUNT_ID</code>. bankAccountId,R_BANKACCOUNT_ID
     */
    public Long getRBankaccountId();

    /**
     * Setter for <code>DB_HTL.HR_REL_EMPLOYEE_BACCOUNT.R_EMPLOYEE_ID</code>. employeeId,R_EMPLOYEE_ID
     */
    public IHrRelEmployeeBaccount setREmployeeId(Long value);

    /**
     * Getter for <code>DB_HTL.HR_REL_EMPLOYEE_BACCOUNT.R_EMPLOYEE_ID</code>. employeeId,R_EMPLOYEE_ID
     */
    public Long getREmployeeId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IHrRelEmployeeBaccount
     */
    public void from(up.god.jooq.tables.interfaces.IHrRelEmployeeBaccount from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IHrRelEmployeeBaccount
     */
    public <E extends up.god.jooq.tables.interfaces.IHrRelEmployeeBaccount> E into(E into);

    default IHrRelEmployeeBaccount fromJson(io.vertx.core.json.JsonObject json) {
        setRBankaccountId(json.getLong("R_BANKACCOUNT_ID"));
        setREmployeeId(json.getLong("R_EMPLOYEE_ID"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("R_BANKACCOUNT_ID",getRBankaccountId());
        json.put("R_EMPLOYEE_ID",getREmployeeId());
        return json;
    }

}
