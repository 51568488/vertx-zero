package io.vertx.quiz.ext.web;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.ext.web.Router;
import io.vertx.quiz.core.VertxTestBase;
import io.vertx.up.log.Annal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class WebTestBase extends VertxTestBase {

    protected static Set<HttpMethod> METHODS = new HashSet<>(Arrays.asList(HttpMethod.DELETE, HttpMethod.GET,
            HttpMethod.HEAD, HttpMethod.PATCH, HttpMethod.OPTIONS, HttpMethod.TRACE, HttpMethod.POST, HttpMethod.PUT));

    protected HttpServer server;
    protected HttpClient client;
    protected Router router;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.router = Router.router(this.vertx);
        this.server = this.vertx.createHttpServer(this.getHttpServerOptions());
        this.client = this.vertx.createHttpClient(this.getHttpClientOptions());
        final CountDownLatch latch = new CountDownLatch(1);
        this.server.requestHandler(this.router::accept).listen(this.onSuccess(res -> latch.countDown()));
        this.awaitLatch(latch);
    }

    protected HttpServerOptions getHttpServerOptions() {
        return new HttpServerOptions().setPort(8080).setHost("localhost");
    }

    protected HttpClientOptions getHttpClientOptions() {
        return new HttpClientOptions().setDefaultPort(8080);
    }

    @Override
    public void tearDown() throws Exception {
        if (this.client != null) {
            try {
                this.client.close();
            } catch (final IllegalStateException e) {
            }
        }
        if (this.server != null) {
            final CountDownLatch latch = new CountDownLatch(1);
            this.server.close((asyncResult) -> {
                this.assertTrue(asyncResult.succeeded());
                latch.countDown();
            });
            this.awaitLatch(latch);
        }
        super.tearDown();
    }

    protected void testRequest(final HttpMethod method, final String path, final int statusCode, final String statusMessage) throws Exception {
        this.testRequest(method, path, null, statusCode, statusMessage, null);
    }

    protected void testRequest(final HttpMethod method, final String path, final int statusCode, final String statusMessage,
                               final String responseBody) throws Exception {
        this.testRequest(method, path, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequest(final HttpMethod method, final String path, final int statusCode, final String statusMessage,
                               final Buffer responseBody) throws Exception {
        this.testRequestBuffer(method, path, null, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequestWithContentType(final HttpMethod method, final String path, final String contentType, final int statusCode, final String statusMessage) throws Exception {
        this.testRequest(method, path, req -> req.putHeader("content-type", contentType), statusCode, statusMessage, null);
    }

    protected void testRequestWithAccepts(final HttpMethod method, final String path, final String accepts, final int statusCode, final String statusMessage) throws Exception {
        this.testRequest(method, path, req -> req.putHeader("accept", accepts), statusCode, statusMessage, null);
    }

    protected void testRequestWithCookies(final HttpMethod method, final String path, final String cookieHeader, final int statusCode, final String statusMessage) throws Exception {
        this.testRequest(method, path, req -> req.putHeader("cookie", cookieHeader), statusCode, statusMessage, null);
    }

    protected void testRequest(final HttpMethod method, final String path, final Consumer<HttpClientRequest> requestAction,
                               final int statusCode, final String statusMessage,
                               final String responseBody) throws Exception {
        this.testRequest(method, path, requestAction, null, statusCode, statusMessage, responseBody);
    }

    protected void testRequest(final HttpMethod method, final String path, final Consumer<HttpClientRequest> requestAction, final Consumer<HttpClientResponse> responseAction,
                               final int statusCode, final String statusMessage,
                               final String responseBody) throws Exception {
        this.testRequestBuffer(method, path, requestAction, responseAction, statusCode, statusMessage, responseBody != null ? Buffer.buffer(responseBody) : null, true);
    }

    protected void testRequestBuffer(final HttpMethod method, final String path, final Consumer<HttpClientRequest> requestAction, final Consumer<HttpClientResponse> responseAction,
                                     final int statusCode, final String statusMessage,
                                     final Buffer responseBodyBuffer) throws Exception {
        this.testRequestBuffer(method, path, requestAction, responseAction, statusCode, statusMessage, responseBodyBuffer, false);
    }

    protected void testRequestBuffer(final HttpMethod method, final String path, final Consumer<HttpClientRequest> requestAction, final Consumer<HttpClientResponse> responseAction,
                                     final int statusCode, final String statusMessage,
                                     final Buffer responseBodyBuffer, final boolean normalizeLineEndings) throws Exception {
        this.testRequestBuffer(this.client, method, 8080, path, requestAction, responseAction, statusCode, statusMessage, responseBodyBuffer, normalizeLineEndings);
    }

    protected void testRequestBuffer(final HttpClient client, final HttpMethod method, final int port, final String path, final Consumer<HttpClientRequest> requestAction, final Consumer<HttpClientResponse> responseAction,
                                     final int statusCode, final String statusMessage,
                                     final Buffer responseBodyBuffer) throws Exception {
        this.testRequestBuffer(client, method, port, path, requestAction, responseAction, statusCode, statusMessage, responseBodyBuffer, false);
    }

    protected void testRequestBuffer(final HttpClient client, final HttpMethod method, final int port, final String path, final Consumer<HttpClientRequest> requestAction, final Consumer<HttpClientResponse> responseAction,
                                     final int statusCode, final String statusMessage,
                                     final Buffer responseBodyBuffer, final boolean normalizeLineEndings) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final HttpClientRequest req = client.request(method, port, "localhost", path, resp -> {
            this.assertEquals(statusCode, resp.statusCode());
            this.assertEquals(statusMessage, resp.statusMessage());
            if (responseAction != null) {
                responseAction.accept(resp);
            }
            if (responseBodyBuffer == null) {
                latch.countDown();
            } else {
                resp.bodyHandler(buff -> {
                    if (normalizeLineEndings) {
                        buff = normalizeLineEndingsFor(buff);
                    }
                    this.assertEquals(responseBodyBuffer, buff);
                    latch.countDown();
                });
            }
        });
        if (requestAction != null) {
            requestAction.accept(req);
        }
        req.end();
        this.awaitLatch(latch);
    }

    protected static Buffer normalizeLineEndingsFor(final Buffer buff) {
        final int buffLen = buff.length();
        final Buffer normalized = Buffer.buffer(buffLen);
        for (int i = 0; i < buffLen; i++) {
            final short unsignedByte = buff.getUnsignedByte(i);
            if (unsignedByte != '\r' || i + 1 == buffLen || buff.getUnsignedByte(i + 1) != '\n') {
                normalized.appendUnsignedByte(unsignedByte);
            }
        }
        return normalized;
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
