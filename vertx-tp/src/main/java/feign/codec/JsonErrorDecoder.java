package feign.codec;

import feign.Response;
import io.vertx.tp.atom.FeignRunException;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Jackson;

import java.io.IOException;

public class JsonErrorDecoder implements ErrorDecoder {

    private static final Annal LOGGER = Annal.get(JsonErrorDecoder.class);

    @Override
    public Exception decode(final String methodKey, final Response response) {
        try {
            final Exception error = Jackson.deserialize(
                    response.body().asInputStream(),
                    FeignRunException.class);
            LOGGER.info("[ ZERO ] Feign result: " + ((null == error) ? null : error.getMessage()));
            return error;
        } catch (final IOException ex) {
            LOGGER.jvm(ex);
            throw new RuntimeException("[ ZERO ] Feign IO exception: " + ex.getMessage());
        }
    }
}
