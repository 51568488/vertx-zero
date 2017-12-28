package io.vertx.up.eon.em;

public enum MessageModel {
    // Request <-> Response
    // Common Http Worker
    REQUEST_RESPONSE,
    // Publish <-> Subscribe
    // Common Publisher
    PUBLISH_SUBSCRIBE,
    // Discovery <-> Publish
    // Micro Discovery Publisher
    DISCOVERY_PUBLISH
}
