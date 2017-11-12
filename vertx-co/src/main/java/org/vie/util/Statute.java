package org.vie.util;

import org.vie.cv.Values;
import org.vie.fun.HBool;
import org.vie.fun.HNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Do some specification statute operations
 */
public final class Statute {
    /**
     * @param list
     * @param fnFilter
     * @param <T>
     * @return
     */
    public static <T> T findUnique(final List<T> list, final Predicate<T> fnFilter) {
        return HNull.get(() -> {
            final List<T> filtered = list.stream()
                    .filter(fnFilter).collect(Collectors.toList());
            return HBool.exec(filtered.isEmpty(),
                    () -> null,
                    () -> filtered.get(Values.IDX));
        }, list, fnFilter);
    }
    
    private Statute() {
    }
}
