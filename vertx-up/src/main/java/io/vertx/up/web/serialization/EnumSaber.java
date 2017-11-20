package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.zero.tool.mirror.Instance;

/**
 * Enum
 */
public class EnumSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof Enum) {
                reference = Instance.invoke(input, "name");
            }
            return reference;
        }, input);
    }
}
