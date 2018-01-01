package io.vertx.quiz;

import io.vertx.up.tool.mirror.Pack;

import java.util.Set;

public class ScanBase {

    private final transient Set<Class<?>> classes;

    public ScanBase() {
        this.classes = Pack.getClasses(null);
    }

    protected Set<Class<?>> getClasses() {
        return this.classes;
    }
}
