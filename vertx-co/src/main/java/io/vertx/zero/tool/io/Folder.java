package io.vertx.zero.tool.io;

import io.vertx.up.func.Fn;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Folder {

    public static List<String> listFiles(final String folder) {
        return listFiles(folder, null);
    }

    public static List<String> listFiles(final String folder, final String extension) {
        return Fn.get(new ArrayList<>(), () -> list(folder, extension, false), folder);
    }

    public static List<String> listDirectories(final String folder) {
        return Fn.get(new ArrayList<>(), () -> list(folder, null, true), folder);
    }

    private static List<String> list(final String folder,
                                     final String extension,
                                     final boolean isDirectory) {
        final URL url = IO.getURL(folder);
        final List<String> retList = new ArrayList<>();
        if (null != url) {
            final File file = new File(url.getFile());
            if (file.isDirectory() && file.exists()) {
                final File[] files = (isDirectory) ?
                        file.listFiles(File::isDirectory) :
                        (null == extension ?
                                file.listFiles(File::isFile) :
                                file.listFiles((item) -> item.isFile()
                                        && item.getName().endsWith(extension)));
                if (null != files) {
                    retList.addAll(Arrays.stream(files)
                            .map(File::getName)
                            .collect(Collectors.toList()));
                }
            }
        }
        return retList;
    }
}
