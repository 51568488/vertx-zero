package io.vertx.zero.hors.run;

import com.vie.hors.ZeroRunException;

import java.text.MessageFormat;

/**
 * vertx:
 * lime: error, inject, db, consul
 */
public class LimeFileException extends ZeroRunException {

    public LimeFileException(final String filename) {
        super(MessageFormat.format(Message.LIME_FILE, filename));
    }
}
