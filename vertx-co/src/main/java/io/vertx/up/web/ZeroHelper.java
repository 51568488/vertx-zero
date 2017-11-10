package io.vertx.up.web;

import io.vertx.up.annotations.Agent;
import org.vie.cv.Values;
import org.vie.cv.em.ServerType;
import org.vie.exception.up.AgentDuplicatedException;
import org.vie.fun.HBool;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author Lang
 */
public class ZeroHelper {

    private static final Annal LOGGER = Annal.get(ZeroHelper.class);

    /** **/
    public static ServerType getAgentKey(final Class<?> clazz) {
        return HBool.exec(Anno.isMark(clazz, Agent.class),
                () -> Instance.invoke(getAgentValue(clazz), Agent.Key.TYPE),
                () -> null);
    }

    /** **/
    public static Annotation getAgentValue(final Class<?> clazz) {
        return Anno.get(clazz, Agent.class);
    }

    /** **/
    public static ConcurrentMap<ServerType, Boolean> isAgentDefined(
            final ConcurrentMap<ServerType, List<Class<?>>> agents,
            final Class<?>... exclude) {
        final Set<Class<?>> excludes = new HashSet<>(Arrays.asList(exclude));
        final ConcurrentMap<ServerType, Boolean> defined
                = new ConcurrentHashMap<>();
        for (final ServerType server : agents.keySet()) {
            final List<Class<?>> item = agents.get(server);
            // Filter to result.
            final List<Class<?>> filtered =
                    item.stream()
                            .filter(each -> !excludes.contains(each))
                            .collect(Collectors.toList());
            // > 1 means duplicated defined
            final int size = filtered.size();
            HBool.execUp(1 < size,
                    LOGGER, AgentDuplicatedException.class,
                    ZeroHelper.class, server, size);
            // == 0 means undefined
            // == 1 means correct defined
            defined.put(server, Values.ONE == size);
        }
        return defined;
    }

    /** **/
    public static Path getPath(final Class<?> clazz) {
        return getPath(Anno.get(clazz, Path.class));
    }

    /** **/
    public static Path getPath(final Method method) {
        return getPath(Anno.get(method, Path.class));
    }

    private static Path getPath(final Annotation anno) {
        return HBool.exec(anno instanceof Path,
                () -> (Path) anno,
                () -> null);
    }
}
