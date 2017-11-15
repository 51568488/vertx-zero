package io.vertx.zero.tool.io.filter;

import io.vertx.up.eon.FileTypes;

import java.io.FileFilter;

/**
 * .class file filter
 */
public class ClassFileFilter extends BaseFilter
        implements FileFilter {
    @Override
    public String getFileExtension() {
        return FileTypes.CLASS;
    }
}
