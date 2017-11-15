package io.vertx.up.rs;

import io.vertx.ext.unit.TestContext;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.example.AnnoAgent;
import io.vertx.up.example.AnnoExceAgent;
import io.vertx.up.exception.up.AgentDuplicatedException;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.ZeroHttpAgent;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class VertxAnnoTc extends UnitBase {

    private static final ConcurrentMap<ServerType, List<Class<?>>>
            AGENTS = ZeroAnno.getAgents();

    private boolean isDefine(final Class<?>... args) {
        final ConcurrentMap<ServerType, Boolean>
                defined = ZeroAnno.isDefined(AGENTS, args);
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
                ZeroHttpAgent.class);
        context.assertTrue(isDefined);
    }

    @Test
    public void testUndefined(final TestContext context) {
        final boolean isDefined = isDefine(AnnoExceAgent.class,
                ZeroHttpAgent.class, AnnoAgent.class);
        context.assertFalse(isDefined);
    }

    @Test(expected = AgentDuplicatedException.class)
    public void testExcpetion() {
        isDefine(ZeroHttpAgent.class);
    }
}
