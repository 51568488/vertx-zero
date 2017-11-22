package io.vertx.core.eventbus;

import io.vertx.core.buffer.Buffer;
import io.vertx.up.atom.EnvelopOld;
import io.vertx.zero.eon.Values;
import io.vertx.zero.tool.io.Stream;

/**
 * Codec to transfer envelop
 */
public final class EnvelopCodec implements MessageCodec<EnvelopOld, EnvelopOld> {

    @Override
    public void encodeToWire(final Buffer buffer,
                             final EnvelopOld message) {
        buffer.appendBytes(Stream.to(message));
    }

    @Override
    public EnvelopOld decodeFromWire(final int i,
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
    public EnvelopOld transform(final EnvelopOld message) {
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
