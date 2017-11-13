package io.vertx.exception.up;

import io.vertx.exception.UpException;
import io.vertx.up.cv.em.ServerType;
import org.vie.util.StringUtil;

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
