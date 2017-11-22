package io.vertx.up.atom;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.ext.auth.User;
import io.vertx.up.exception.WebException;

import java.io.Serializable;

/**
 * New resource model to parse the data
 */
public class Envelop implements Serializable {

    private final HttpStatusCode status = HttpStatusCode.OK;

    private MultiMap headers;

    private WebException error;

    private byte[] data;

    private User user;
}
