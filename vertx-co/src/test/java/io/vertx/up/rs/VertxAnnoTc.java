package io.vertx.up.rs;

import com.vie.cv.em.ServerType;
import com.vie.exception.up.AgentDuplicatedException;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.web.HttpAgent;
import org.junit.Test;
import top.UnitBase;
import top.example.AnnoAgent;
import top.example.AnnoExceAgent;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class VertxAnnoTc extends UnitBase {

    private static final ConcurrentMap<ServerType, List<Class<?>>>
            AGENTS = VertxAnno.getAgents();

    private boolean isDefine(final Class<?>... args) {
        final ConcurrentMap<ServerType, Boolean>
                defined = VertxAnno.isDefined(AGENTS, args);
        return defined.get(ServerType.HTTP);
    }

    @Test
    public void testAnno(final TestContext context) {
        System.out.println(AGENTS);
        context.assertNotNull(AGENTS);
    }

    @Test
    public void testDefined(final TestContext context) {
        final boolean isDefined = isDefine(AnnoExceAgent.class,
                HttpAgent.class);
        context.assertTrue(isDefined);
    }

    @Test
    public void testUndefined(final TestContext context) {
        final boolean isDefined = isDefine(AnnoExceAgent.class,
                HttpAgent.class, AnnoAgent.class);
        context.assertFalse(isDefined);
    }

    @Test(expected = AgentDuplicatedException.class)
    public void testExcpetion() {
        isDefine(HttpAgent.class);
    }
}
