package zava.io;

import io.vertx.zero.eon.FileSuffix;

import java.io.FileFilter;

/**
 * .class file filter
 */
public class ClassFileFilter extends BaseFilter
        implements FileFilter {
    @Override
    public String getFileExtension() {
        return FileSuffix.CLASS;
    }
}
