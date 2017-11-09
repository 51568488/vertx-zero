package com.vie.core.io;

import com.vie.cv.Strings;

import java.io.File;

/**
 * File filter structure
 */
public abstract class BaseFilter {

    private transient boolean recursive = true;

    protected BaseFilter() {
        this(true);
    }

    protected BaseFilter(final boolean recursive) {
        this.recursive = recursive;
    }

    public boolean accept(final File file) {
        return (this.recursive && file.isDirectory())
                || (file.getName().endsWith(Strings.DOT + getFileExtension()));
    }

    public abstract String getFileExtension();
}
