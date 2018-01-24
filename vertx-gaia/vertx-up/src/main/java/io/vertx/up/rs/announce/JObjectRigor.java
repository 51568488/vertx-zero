package io.vertx.up.rs.announce;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Rule;
import io.vertx.up.exception.WebException;
import io.vertx.up.rs.regular.Ruler;

import java.util.List;
import java.util.Map;

public class JObjectRigor implements Rigor {

    @Override
    public WebException verify(final Map<String, List<Rule>> rulers,
                               final Object body) {
        WebException error = null;
        if (!rulers.isEmpty()) {
            // Extract first element to JsonObject
            if (null != body) {
                final JsonObject data = (JsonObject) body;
                // Verify the whole JsonObject
                for (final String field : rulers.keySet()) {
                    final Object value = data.getValue(field);
                    final List<Rule> rules = rulers.get(field);
                    // Verify each field.
                    error = this.verify(rules, field, value);
                }
            }
        }
        return error;
    }

    private WebException verify(final List<Rule> rules,
                                final String field,
                                final Object value) {
        WebException error = null;
        for (final Rule rule : rules) {
            final Ruler ruler = Ruler.get(rule.getType());
            if (null != ruler) {
                error = ruler.verify(field, value, rule);
            }
            // Error found.
            if (null != error) {
                break;
            }
        }
        return error;
    }
}
