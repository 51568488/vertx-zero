package up.god.jooq;

import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.annotations.EndPoint;
import org.jooq.Configuration;
import up.god.jooq.domain.tables.daos.SecUserDao;

import javax.inject.infix.Jooq;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/atlas")
@EndPoint
public class TestActor {

    @Jooq
    private transient Configuration client;

    @Path("/say")
    @GET
    public String sayHell() {
        System.out.println(this.client);
        final SecUserDao dao = JooqInfix.getDao(SecUserDao.class);
        System.out.println(dao.fetchByIsActive(true));
        return "Hello Jooq";
    }
}
