package io.vertx.up.exception.web;

import io.vertx.up.exception.WebException;
import io.vertx.up.tool.StringUtil;

import javax.ws.rs.core.MediaType;
import java.util.Set;

public class _415MediaNotSupportException extends WebException {

    public _415MediaNotSupportException(final Class<?> clazz,
                                        final MediaType media,
                                        final Set<MediaType> expected) {
        super(clazz, media.toString(), StringUtil.join(expected.toArray(new MediaType[]{})));
    }

    @Override
    public int getCode() {
        return -60006;
    }
}
