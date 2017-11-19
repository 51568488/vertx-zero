package io.vertx.zero.tool.io;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.up.func.Fn;
import io.vertx.zero.exception.EmptyStreamException;
import io.vertx.zero.log.Log;

import java.io.*;

/**
 * Stream read class.
 */
public final class Stream {
    /**
     * Codec usage
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> byte[] to(final T message) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        return Fn.getJvm(new byte[0], () -> {
            final ObjectOutputStream out = new ObjectOutputStream(bytes);
            out.writeObject(message);
            out.close();
            return bytes.toByteArray();
        }, bytes);
    }

    /**
     * Codec usage
     *
     * @param pos
     * @param buffer
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T from(final int pos, final Buffer buffer) {
        final ByteArrayInputStream stream = new ByteArrayInputStream(buffer.getBytes());
        return Fn.getJvm(null, () -> {
            final ObjectInputStream in = new ObjectInputStream(stream);
            return (T) in.readObject();
        }, stream);
    }

    /**
     * Direct read by vert.x logger to avoid dead lock.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(Stream.class);

    /**
     * @param filename
     * @return
     */
    public static InputStream read(final String filename) {
        return read(filename, null);
    }

    /**
     * Ensure read from path
     * 1. Read from current folder
     * 2. clazz == null: Read from class loader
     * 3. clazz != null: Read from clazz's class loader
     *
     * @param filename
     * @param clazz
     * @return
     */
    public static InputStream read(final String filename,
                                   final Class<?> clazz) {
        final File file = new File(filename);
        Log.debug(LOGGER, Info.INF_CUR, file.exists());
        final InputStream in = Fn.getSemi(file.exists(), null,
                () -> in(file),
                () -> (null == clazz) ? in(filename) : in(filename, clazz));
        Log.debug(LOGGER, Info.INF_PATH, filename, in);
        if (null == in) {
            throw new EmptyStreamException(filename);
        }
        return in;
    }

    /**
     * Stream read from file object
     * new FileInputStream(file)
     *
     * @param file
     * @return
     */
    public static InputStream in(final File file) {
        return Fn.getJvm(() -> (file.exists() && file.isFile())
                ? new FileInputStream(file) : null, file);
    }

    /**
     * Stream read from clazz
     * clazz.getResourceAsStream(filename)
     *
     * @param filename
     * @param clazz
     * @return
     */
    public static InputStream in(final String filename,
                                 final Class<?> clazz) {
        return Fn.getJvm(
                () -> clazz.getResourceAsStream(filename), clazz, filename);
    }

    /**
     * Stream read from Thread Class Loader
     * Thread.currentThread().getContextClassLoader()
     *
     * @param filename
     * @return
     */
    public static InputStream in(final String filename) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return Fn.getJvm(
                () -> loader.getResourceAsStream(filename), filename);
    }

    private Stream() {
    }
}
