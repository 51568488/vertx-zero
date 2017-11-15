package io.vertx.quiz.core;

import io.vertx.core.*;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.*;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.quiz.fakecluster.FakeClusterManager;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class VertxTestBase extends AsyncTestBase {

    public static final boolean USE_NATIVE_TRANSPORT = Boolean.getBoolean("vertx.useNativeTransport");
    public static final boolean USE_DOMAIN_SOCKETS = Boolean.getBoolean("vertx.useDomainSockets");
    private static final Logger log = LoggerFactory.getLogger(VertxTestBase.class);

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    protected Vertx vertx;

    protected Vertx[] vertices;

    private List<Vertx> created;

    protected void vinit() {
        this.vertx = null;
        this.vertices = null;
        this.created = null;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        vinit();
        final VertxOptions options = getOptions();
        final boolean nativeTransport = options.getPreferNativeTransport();
        this.vertx = Vertx.vertx(options);
        if (nativeTransport) {
            assertTrue(this.vertx.isNativeTransportEnabled());
        }
    }

    protected VertxOptions getOptions() {
        final VertxOptions options = new VertxOptions();
        options.setPreferNativeTransport(USE_NATIVE_TRANSPORT);
        return options;
    }

    @Override
    protected void tearDown() throws Exception {
        if (this.vertx != null) {
            close(this.vertx);
        }
        if (this.created != null) {
            final CountDownLatch latch = new CountDownLatch(this.created.size());
            for (final Vertx v : this.created) {
                v.close(ar -> {
                    if (ar.failed()) {
                        log.error("Failed to shutdown vert.x", ar.cause());
                    }
                    latch.countDown();
                });
            }
            assertTrue(latch.await(180, TimeUnit.SECONDS));
        }
        FakeClusterManager.reset(); // Bit ugly
        super.tearDown();
    }

    /**
     * @return create a blank new Vert.x instance with no options closed when tear down executes.
     */
    protected Vertx vertx() {
        if (this.created == null) {
            this.created = new ArrayList<>();
        }
        final Vertx vertx = Vertx.vertx();
        this.created.add(vertx);
        return vertx;
    }

    /**
     * @return create a blank new Vert.x instance with @{@code options} closed when tear down executes.
     */
    protected Vertx vertx(final VertxOptions options) {
        if (this.created == null) {
            this.created = new ArrayList<>();
        }
        final Vertx vertx = Vertx.vertx(options);
        this.created.add(vertx);
        return vertx;
    }

    /**
     * Create a blank new clustered Vert.x instance with @{@code options} closed when tear down executes.
     */
    protected void clusteredVertx(final VertxOptions options, final Handler<AsyncResult<Vertx>> ar) {
        if (this.created == null) {
            this.created = Collections.synchronizedList(new ArrayList<>());
        }
        Vertx.clusteredVertx(options, event -> {
            if (event.succeeded()) {
                this.created.add(event.result());
            }
            ar.handle(event);
        });
    }

    protected ClusterManager getClusterManager() {
        return null;
    }

    protected void startNodes(final int numNodes) {
        startNodes(numNodes, getOptions());
    }

    protected void startNodes(final int numNodes, final VertxOptions options) {
        final CountDownLatch latch = new CountDownLatch(numNodes);
        this.vertices = new Vertx[numNodes];
        for (int i = 0; i < numNodes; i++) {
            final int index = i;
            clusteredVertx(options.setClusterHost("localhost").setClusterPort(0).setClustered(true)
                    .setClusterManager(getClusterManager()), ar -> {
                try {
                    if (ar.failed()) {
                        ar.cause().printStackTrace();
                    }
                    assertTrue("Failed to start node", ar.succeeded());
                    this.vertices[index] = ar.result();
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            assertTrue(latch.await(2, TimeUnit.MINUTES));
        } catch (final InterruptedException e) {
            fail(e.getMessage());
        }
    }


    protected static void setOptions(final TCPSSLOptions sslOptions, final KeyCertOptions options) {
        if (options instanceof JksOptions) {
            sslOptions.setKeyStoreOptions((JksOptions) options);
        } else if (options instanceof PfxOptions) {
            sslOptions.setPfxKeyCertOptions((PfxOptions) options);
        } else {
            sslOptions.setPemKeyCertOptions((PemKeyCertOptions) options);
        }
    }

    protected static final String[] ENABLED_CIPHER_SUITES =
            new String[]{
                    "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
                    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
                    "TLS_RSA_WITH_AES_128_CBC_SHA256",
                    "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
                    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
                    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
                    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
                    "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                    "TLS_RSA_WITH_AES_128_CBC_SHA",
                    "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                    "SSL_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
                    "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
                    "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
                    "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
                    "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
                    "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
                    "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
                    "SSL_RSA_WITH_RC4_128_MD5",
                    "TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
                    "TLS_DH_anon_WITH_AES_128_GCM_SHA256",
                    "TLS_DH_anon_WITH_AES_128_CBC_SHA256",
                    "TLS_ECDH_anon_WITH_AES_128_CBC_SHA",
                    "TLS_DH_anon_WITH_AES_128_CBC_SHA",
                    "TLS_ECDH_anon_WITH_RC4_128_SHA",
                    "SSL_DH_anon_WITH_RC4_128_MD5",
                    "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA",
                    "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",
                    "TLS_RSA_WITH_NULL_SHA256",
                    "TLS_ECDHE_ECDSA_WITH_NULL_SHA",
                    "TLS_ECDHE_RSA_WITH_NULL_SHA",
                    "SSL_RSA_WITH_NULL_SHA",
                    "TLS_ECDH_ECDSA_WITH_NULL_SHA",
                    "TLS_ECDH_RSA_WITH_NULL_SHA",
                    "TLS_ECDH_anon_WITH_NULL_SHA",
                    "SSL_RSA_WITH_NULL_MD5",
                    "SSL_RSA_WITH_DES_CBC_SHA",
                    "SSL_DHE_RSA_WITH_DES_CBC_SHA",
                    "SSL_DHE_DSS_WITH_DES_CBC_SHA",
                    "SSL_DH_anon_WITH_DES_CBC_SHA",
                    "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
                    "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",
                    "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
                    "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
                    "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA",
                    "SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA",
                    "TLS_KRB5_WITH_RC4_128_SHA",
                    "TLS_KRB5_WITH_RC4_128_MD5",
                    "TLS_KRB5_WITH_3DES_EDE_CBC_SHA",
                    "TLS_KRB5_WITH_3DES_EDE_CBC_MD5",
                    "TLS_KRB5_WITH_DES_CBC_SHA",
                    "TLS_KRB5_WITH_DES_CBC_MD5",
                    "TLS_KRB5_EXPORT_WITH_RC4_40_SHA",
                    "TLS_KRB5_EXPORT_WITH_RC4_40_MD5",
                    "TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA",
                    "TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5"
            };

    /**
     * Create a worker verticle for the current Vert.x and return its context.
     *
     * @return the context
     * @throws Exception anything preventing the creation of the worker
     */
    protected Context createWorker() throws Exception {
        final CompletableFuture<Context> fut = new CompletableFuture<>();
        this.vertx.deployVerticle(new AbstractVerticle() {
            @Override
            public void start() throws Exception {
                fut.complete(this.context);
            }
        }, new DeploymentOptions().setWorker(true), ar -> {
            if (ar.failed()) {
                fut.completeExceptionally(ar.cause());
            }
        });
        return fut.get();
    }

    /**
     * Create worker verticles for the current Vert.x and returns the list of their contexts.
     *
     * @param num the number of verticles to create
     * @return the contexts
     * @throws Exception anything preventing the creation of the workers
     */
    protected List<Context> createWorkers(final int num) throws Exception {
        final List<Context> contexts = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            contexts.add(createWorker());
        }
        return contexts;
    }
}
