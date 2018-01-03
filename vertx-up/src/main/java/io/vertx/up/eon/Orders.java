package io.vertx.up.eon;

/**
 * Default order for manage standard request flow
 */
public interface Orders {
    /**
     * Cors Order
     * 1,100,000
     **/
    int CORS = 1100000;
    /**
     * Cookie Order
     * 1,200,000
     */
    int COOKIE = 1200000;
    /**
     * Body Order
     * 1,300,000
     */
    int BODY = 1300000;
    /**
     * Pattern
     * 1,400,000
     */
    int PATTERN = 1400000;
    /**
     * Acceptor
     * 1,500,000
     */
    int ACCEPTOR = 1500000;
    /**
     * Secure
     * 1,600,000
     */
    int SESSION = 1600000;
    /**
     * Session
     * 1,700,000
     */
    int USER_SESSION = 1700000;
    /**
     * User Session
     * 1,800,000
     */
    int SECURE = 1800000;
    /**
     * Filter for request
     * 1,900,000
     */
    int FILTER = 1900000;
    /**
     * Sinker for request ( Normalize Request )
     * 2,000,000
     */
    int NORMALIZE = 2000000;
    /**
     * Sign for request ( Sign Request )
     * 3,000,000
     */
    int SIGN = 3000000;
    /**
     * ( Default order for event )
     * 5,000,000
     */
    int EVENT = 5000000;
    /**
     * Event bus sender ( The last one )
     * 6,000,000
     */
    int SENDER = 6000000;
}
