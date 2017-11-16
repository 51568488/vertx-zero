package io.vertx.up.web.thread;

import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Anno;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InjectThread extends Thread {

    private static final Annal LOGGER = Annal.get(InjectThread.class);

    private final ConcurrentMap<String, Field> fieldMap = new ConcurrentHashMap<>();

    private final transient Class<?> reference;

    public InjectThread(final Class<?> clazz) {
        this.setName("zero-injection-scanner-" + this.getId());
        this.reference = clazz;
    }

    @Override
    public void run() {
        if (null != this.reference) {
            this.fieldMap.putAll(Anno.getPlugins(this.reference, Inject.class));
            if (!this.fieldMap.isEmpty()) {
                LOGGER.info(Info.SCANED_EVENTS, this.reference.getName(),
                        this.fieldMap.size());
            }
        }
    }

    public ConcurrentMap<String, Field> getFieldMap() {
        return this.fieldMap;
    }

    public Class<?> getClassKey() {
        return this.reference;
    }

    public boolean isEmpty() {
        return this.fieldMap.isEmpty();
    }
}
