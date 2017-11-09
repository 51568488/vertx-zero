package com.vie.core.io;

import com.vie.cv.FileTypes;

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
