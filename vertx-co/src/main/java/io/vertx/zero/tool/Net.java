package io.vertx.zero.tool;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Net {

    private final static Annal LOGGER = Annal.get(Net.class);

    /**
     * Check whether host:port is ok to connect
     *
     * @param host
     * @param port
     * @return
     */
    public static boolean isReach(final String host, final int port) {
        final Boolean reach = Fn.getJvm(() -> {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
            return Boolean.TRUE;
        }, host, port);
        return null == reach ? Boolean.FALSE : Boolean.TRUE;
    }
}
