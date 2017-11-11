package io.vertx.up.ce.codec;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.up.ce.Envelop;
import org.vie.cv.Values;
import org.vie.util.io.Stream;

/**
 * Codec to transfer envelop
 */
public final class EnvelopCodec implements MessageCodec<Envelop, Envelop> {

    @Override
    public void encodeToWire(final Buffer buffer,
                             final Envelop message) {
        buffer.appendBytes(Stream.to(message));
    }

    @Override
    public Envelop decodeFromWire(final int i,
                                  final Buffer buffer) {
        return Stream.from(i, buffer);
    }

    /**
     * Local usage
     *
     * @param message
     * @return
     */
    @Override
    public Envelop transform(final Envelop message) {
        return message;
    }

    @Override
    public String name() {
        return getClass().getName();
    }

    @Override
    public byte systemCodecID() {
        return Values.CODECS;
    }
}
