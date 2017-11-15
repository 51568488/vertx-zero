package io.vertx.up.exception;

import io.vertx.up.eon.em.ServerType;
import io.vertx.zero.exception.UpException;
import io.vertx.zero.tool.StringUtil;

import java.util.Set;

public class AgentDuplicatedException extends UpException {

    public AgentDuplicatedException(final Class<?> clazz,
                                    final ServerType type,
                                    final int numbber,
                                    final Set<String> agents) {
        super(clazz, numbber, type, StringUtil.join(agents));
    }

    @Override
    public int getCode() {
        return -40004;
    }
}
