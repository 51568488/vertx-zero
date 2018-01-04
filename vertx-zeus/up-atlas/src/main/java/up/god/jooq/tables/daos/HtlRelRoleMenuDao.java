/*
 * This file is generated by jOOQ.
*/
package up.god.jooq.tables.daos;


import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;

import up.god.jooq.tables.HtlRelRoleMenu;
import up.god.jooq.tables.records.HtlRelRoleMenuRecord;


import java.util.concurrent.CompletableFuture;
import io.github.jklingsporn.vertx.jooq.future.util.FutureTool;
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
public class HtlRelRoleMenuDao extends DAOImpl<HtlRelRoleMenuRecord, up.god.jooq.tables.pojos.HtlRelRoleMenu, Record2<String, String>> implements VertxDAO<up.god.jooq.tables.records.HtlRelRoleMenuRecord,up.god.jooq.tables.pojos.HtlRelRoleMenu,org.jooq.Record2<java.lang.String, java.lang.String>> {

    /**
     * Create a new HtlRelRoleMenuDao without any configuration
     */
    public HtlRelRoleMenuDao() {
        super(HtlRelRoleMenu.HTL_REL_ROLE_MENU, up.god.jooq.tables.pojos.HtlRelRoleMenu.class);
    }

    /**
     * Create a new HtlRelRoleMenuDao with an attached configuration
     */
    public HtlRelRoleMenuDao(Configuration configuration) {
        super(HtlRelRoleMenu.HTL_REL_ROLE_MENU, up.god.jooq.tables.pojos.HtlRelRoleMenu.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<String, String> getId(up.god.jooq.tables.pojos.HtlRelRoleMenu object) {
        return compositeKeyRecord(object.getRRoleId(), object.getRMenuId());
    }

    /**
     * Fetch records that have <code>R_ROLE_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRelRoleMenu> fetchByRRoleId(String... values) {
        return fetch(HtlRelRoleMenu.HTL_REL_ROLE_MENU.R_ROLE_ID, values);
    }

    /**
     * Fetch records that have <code>R_MENU_ID IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRelRoleMenu> fetchByRMenuId(String... values) {
        return fetch(HtlRelRoleMenu.HTL_REL_ROLE_MENU.R_MENU_ID, values);
    }

    /**
     * Fetch records that have <code>IS_ORIGINAL IN (values)</code>
     */
    public List<up.god.jooq.tables.pojos.HtlRelRoleMenu> fetchByIsOriginal(Boolean... values) {
        return fetch(HtlRelRoleMenu.HTL_REL_ROLE_MENU.IS_ORIGINAL, values);
    }

    /**
     * Fetch records that have <code>R_ROLE_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRelRoleMenu>> fetchByRRoleIdAsync(List<String> values) {
        return fetchAsync(HtlRelRoleMenu.HTL_REL_ROLE_MENU.R_ROLE_ID,values);
    }

    /**
     * Fetch records that have <code>R_MENU_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRelRoleMenu>> fetchByRMenuIdAsync(List<String> values) {
        return fetchAsync(HtlRelRoleMenu.HTL_REL_ROLE_MENU.R_MENU_ID,values);
    }

    /**
     * Fetch records that have <code>IS_ORIGINAL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<up.god.jooq.tables.pojos.HtlRelRoleMenu>> fetchByIsOriginalAsync(List<Boolean> values) {
        return fetchAsync(HtlRelRoleMenu.HTL_REL_ROLE_MENU.IS_ORIGINAL,values);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
