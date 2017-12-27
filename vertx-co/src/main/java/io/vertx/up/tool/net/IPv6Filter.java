package io.vertx.up.tool.net;

public class IPv6Filter implements IPFilter {
    private static IPFilter instance = null;

    /**
     * Access Control
     */
    private IPv6Filter() {
    }

    /**
     * Ignore multiple thread sync problem in extreme case
     */
    public static IPFilter getInstance() {
        if (instance == null) {
            instance = new IPv6Filter();
        }
        return instance;
    }

    @Override
    public boolean accept(final String ipAddress) {
        return ipAddress != null && ipAddress.contains(IPv6KeyWord);
    }
}
