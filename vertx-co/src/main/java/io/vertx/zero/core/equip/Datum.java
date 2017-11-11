package io.vertx.zero.core.equip;

interface Message {

    String INF_B_VERIFY = "[ZERO-CFG] The raw data ( node = {0} ) before validation is {1}.";
}

interface Files {
    /**
     * Vertx
     **/
    String VERTX = "vertx";
    /**
     * Server
     */
    String SERVER = "server";
}

interface Key {
    /**
     * Vertx
     */
    String VERTX = "vertx";
    /**
     * Server
     */
    String SERVER = "server";
}
