package io.vertx.up.rs.announce;

import io.vertx.up.atom.Rule;
import io.vertx.up.exception.WebException;

import java.util.List;
import java.util.Map;

/**
 * Verify special workflow
 */
public class FileRigor implements Rigor {

    @Override
    public WebException verify(final Map<String, List<Rule>> rulers,
                               final Object body) {
        final WebException error = null;
        if (!rulers.isEmpty()) {
            // TODO: Validation Do not extract any element
        }
        return error;
    }
}
