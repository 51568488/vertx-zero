package io.vertx.up.tool.net;

public class IPAllFilter implements IPFilter {
    private static IPFilter instance = null;

    /**
     * Access Control
     */
    private IPAllFilter() {
    }

    ;

    /**
     * Ignore multiple thread sync problem in extreme case
     */
    public static IPFilter getInstance() {
        if (instance == null) {
            instance = new IPAllFilter();
        }
        return instance;
    }

    @Override
    public boolean accept(final String ipAddress) {
        return true;
    }
}
