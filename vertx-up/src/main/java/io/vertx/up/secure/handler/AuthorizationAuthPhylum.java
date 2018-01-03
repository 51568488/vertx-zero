package io.vertx.up.secure.handler;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;

abstract class AuthorizationAuthPhylum extends AuthPhylum {
    // this should match the IANA registry: https://www.iana.org/assignments/http-authschemes/http-authschemes.xhtml
    enum Type {
        BASIC("Basic"),
        DIGEST("Digest"),
        BEARER("Bearer"),
        // these have no known implementation
        HOBA("HOBA"),
        MUTUAL("Mutual"),
        NEGOTIATE("Negotiate"),
        OAUTH("OAuth"),
        SCRAM_SHA_1("SCRAM-SHA-1"),
        SCRAM_SHA_256("SCRAM-SHA-256");

        private final String label;

        Type(final String label) {
            this.label = label;
        }

        public boolean is(final String other) {
            return this.label.equalsIgnoreCase(other);
        }
    }

    protected final AuthorizationAuthPhylum.Type type;

    AuthorizationAuthPhylum(final AuthProvider authProvider, final AuthorizationAuthPhylum.Type type) {
        super(authProvider);
        this.type = type;
    }

    AuthorizationAuthPhylum(final AuthProvider authProvider, final String realm, final AuthorizationAuthPhylum.Type type) {
        super(authProvider, realm);
        this.type = type;
    }

    protected final void parseAuthorization(final RoutingContext ctx, final boolean optional, final Handler<AsyncResult<String>> handler) {

        final HttpServerRequest request = ctx.request();
        final String authorization = request.headers().get(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            // The modification for default implementation in vert.x
            handler.handle(Future.failedFuture(this.UNAUTHORIZED));
            return;
        }

        try {
            final int idx = authorization.indexOf(' ');

            if (idx <= 0) {
                handler.handle(Future.failedFuture(this.BAD_REQUEST));
                return;
            }

            if (!this.type.is(authorization.substring(0, idx))) {
                handler.handle(Future.failedFuture(this.UNAUTHORIZED));
                return;
            }

            handler.handle(Future.succeededFuture(authorization.substring(idx + 1)));
        } catch (final RuntimeException e) {
            handler.handle(Future.failedFuture(e));
        }
    }
}
