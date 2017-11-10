package org.vie.exception.ensure;

import io.vertx.core.json.JsonObject;
import org.vie.exception.DemonException;

public class RequiredFieldException extends DemonException {

    public RequiredFieldException(final Class<?> clazz,
                                  final JsonObject data,
                                  final String field) {
        super(clazz, data.encode(), field);
    }

    @Override
    public int getCode() {
        return -10002;
    }
}
