package org.vie.core.io;

import org.vie.cv.FileTypes;

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
