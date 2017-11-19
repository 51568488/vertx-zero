package io.vertx.up.web.origin;

import io.vertx.up.annotations.Queue;
import io.vertx.up.eon.Info;
import io.vertx.up.log.Annal;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Queue
 */
public class QueueInquirer implements Inquirer<Set<Class<?>>> {
    private static final Annal LOGGER = Annal.get(QueueInquirer.class);

    @Override
    public Set<Class<?>> scan(final Set<Class<?>> classes) {
        final Set<Class<?>> queues = classes.stream()
                .filter((item) -> item.isAnnotationPresent(Queue.class))
                .collect(Collectors.toSet());
        LOGGER.info(Info.SCANED_QUEUE, queues.size());
        return queues;
    }
}
