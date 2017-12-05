package io.vertx.up.atom;

import io.vertx.up.eon.ID;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Filler;
import io.vertx.up.tool.Statute;
import io.vertx.up.tool.mirror.Instance;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * FeignDepot to extract the method parameters
 */
public class Depot implements Serializable {

    private List<Class<?>> paramTypes = new ArrayList<>();

    private List<Object> paramValues = new ArrayList<>();

    private final List<String> paramNames = new ArrayList<>();

    private final Event event;

    private final List<Class<? extends Annotation>> paramAnnos = new ArrayList<>();

    public static Depot create(final Event event) {
        return new Depot(event);
    }

    private Depot(final Event event) {
        // 1. Extract types for parameters
        initTypes(event.getAction());
        // 2. Extract annotation for parameters
        initAnnotationsWithName(event.getAction());
        // 3. Reference to event
        this.event = event;
    }

    private void initTypes(final Method method) {
        final Class<?>[] paramTypes = method.getParameterTypes();
        this.paramTypes = Arrays.asList(paramTypes);
    }

    private void initAnnotationsWithName(final Method method) {
        final Annotation[][] annotations = method.getParameterAnnotations();
        Fn.itArray(annotations, (annotationArr, index) -> {
            // Find annotation class
            final Annotation annotation = findAnnotation(annotationArr);
            final Class<? extends Annotation> annoCls = (null == annotation)
                    ? null : annotation.annotationType();
            this.paramAnnos.add(annoCls);
            // Check names
            if (null != annoCls) {
                if (Filler.NO_VALUE.contains(annoCls)) {
                    this.paramNames.add(ID.DIRECT);
                } else {
                    final String name = Instance.invoke(annotation, "value");
                    this.paramNames.add(name);
                }
            } else {
                this.paramNames.add(ID.IGNORE);
            }
            // Besure the params are length match others.
            this.paramValues.add(null);
        });
    }

    private Annotation findAnnotation(final Annotation[] annotations) {
        Annotation annotationCls = null;
        for (final Annotation annotation : annotations) {
            if (Filler.PARAMS.containsKey(annotation.annotationType())) {
                annotationCls = annotation;
            }
        }
        return annotationCls;
    }

    public ConcurrentMap<String, Class<?>> getTypes() {
        return Statute.zipper(this.paramNames, this.paramTypes);
    }

    public ConcurrentMap<String, Object> getValues() {
        return Statute.zipper(this.paramNames, this.paramValues);
    }

    public ConcurrentMap<String, Class<? extends Annotation>> getAnnotations() {
        return Statute.zipper(this.paramNames, this.paramAnnos);
    }

    public void setParamValues(final Object[] parameters) {
        this.paramValues = Arrays.asList(parameters);
    }

    public Event getEvent() {
        return this.event;
    }
}
