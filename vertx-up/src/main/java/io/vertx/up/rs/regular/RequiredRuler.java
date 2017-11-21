package io.vertx.up.rs.regular;

import io.vertx.up.atom.Rule;
import io.vertx.up.exception.WebException;
import io.vertx.zero.tool.StringUtil;

/**
 * {
 * "type":"required",
 * "message":"xxx"
 * "config": "None"
 * }
 */
class RequiredRuler extends BaseRuler {

    @Override
    public WebException verify(final String field,
                               final Object value,
                               final Rule rule) {
        WebException error = null;
        if (null == value || StringUtil.isNil(value.toString())) {
            error = failure(field, value, rule);
        }
        return error;
    }
}
