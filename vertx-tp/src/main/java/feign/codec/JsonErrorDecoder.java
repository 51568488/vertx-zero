package feign.codec;

import feign.Response;
import io.vertx.tp.atom.FeignRunException;
import io.vertx.up.log.Annal;
import io.vertx.zero.tool.Jackson;

import java.io.IOException;

public class JsonErrorDecoder implements ErrorDecoder {

    private static final Annal LOGGER = Annal.get(JsonErrorDecoder.class);

    @Override
    public Exception decode(final String methodKey, final Response response) {
        try {
            return Jackson.deserialize(response.body().asInputStream(), FeignRunException.class);
        } catch (final IOException ex) {
            LOGGER.jvm(ex);
            throw new RuntimeException("[ ZERO ] Feign IO exception: " + ex.getMessage());
        }
    }
}
