package io.vertx.quiz.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.internal.ArrayComparisonFailure;
import org.junit.rules.TestName;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class AsyncTestBase {

    private static final Logger log = LoggerFactory.getLogger(AsyncTestBase.class);

    private CountDownLatch latch;
    private volatile Throwable throwable;
    private volatile Thread thrownThread;
    private volatile boolean testCompleteCalled;
    private volatile boolean awaitCalled;
    private boolean threadChecksEnabled = true;
    private volatile boolean tearingDown;
    private volatile String mainThreadName;
    private final Map<String, Exception> threadNames = new ConcurrentHashMap<>();
    @Rule
    public TestName name = new TestName();


    protected void setUp() throws Exception {
        log.info("Starting test: " + this.getClass().getSimpleName() + "#" + this.name.getMethodName());
        this.mainThreadName = Thread.currentThread().getName();
        this.tearingDown = false;
        waitFor(1);
        this.throwable = null;
        this.testCompleteCalled = false;
        this.awaitCalled = false;
        this.threadNames.clear();
    }

    protected void tearDown() throws Exception {
        this.tearingDown = true;
        afterAsyncTestBase();
    }

    @Before
    public void before() throws Exception {
        setUp();
    }

    @After
    public void after() throws Exception {
        tearDown();
    }

    protected synchronized void waitFor(final int count) {
        this.latch = new CountDownLatch(count);
    }

    protected synchronized void waitForMore(final int count) {
        this.latch = new CountDownLatch(count + (int) this.latch.getCount());
    }

    protected synchronized void complete() {
        if (this.tearingDown) {
            throw new IllegalStateException("testComplete called after test has completed");
        }
        checkThread();
        if (this.testCompleteCalled) {
            throw new IllegalStateException("already complete");
        }
        this.latch.countDown();
        if (this.latch.getCount() == 0) {
            this.testCompleteCalled = true;
        }
    }

    protected void testComplete() {
        if (this.tearingDown) {
            throw new IllegalStateException("testComplete called after test has completed");
        }
        checkThread();
        if (this.testCompleteCalled) {
            throw new IllegalStateException("testComplete() already called");
        }
        this.testCompleteCalled = true;
        this.latch.countDown();
    }

    protected void await() {
        await(2, TimeUnit.MINUTES);
    }

    public void await(final long delay, final TimeUnit timeUnit) {
        if (this.awaitCalled) {
            throw new IllegalStateException("await() already called");
        }
        this.awaitCalled = true;
        try {
            final boolean ok = this.latch.await(delay, timeUnit);
            if (!ok) {
                // timed out
                throw new IllegalStateException("Timed out in waiting for test complete");
            } else {
                rethrowError();
            }
        } catch (final InterruptedException e) {
            throw new IllegalStateException("Test thread was interrupted!");
        }
    }

    private void rethrowError() {
        if (this.throwable != null) {
            if (this.throwable instanceof Error) {
                throw (Error) this.throwable;
            } else if (this.throwable instanceof RuntimeException) {
                throw (RuntimeException) this.throwable;
            } else {
                // Unexpected throwable- Should never happen
                throw new IllegalStateException(this.throwable);
            }

        }
    }

    protected void disableThreadChecks() {
        this.threadChecksEnabled = false;
    }

    protected void afterAsyncTestBase() {
        if (this.throwable != null && this.thrownThread != Thread.currentThread() && !this.awaitCalled) {
            // Throwable caught from non main thread
            throw new IllegalStateException("Assert or failure from non main thread but no await() on main thread", this.throwable);
        }
        for (final Map.Entry<String, Exception> entry : this.threadNames.entrySet()) {
            if (!entry.getKey().equals(this.mainThreadName)) {
                if (this.threadChecksEnabled && !entry.getKey().startsWith("vert.x-")) {
                    final IllegalStateException is = new IllegalStateException("Non Vert.x thread! :" + entry.getKey());
                    is.setStackTrace(entry.getValue().getStackTrace());
                    throw is;
                }
            }
        }

    }

    private void handleThrowable(final Throwable t) {
        if (this.tearingDown) {
            throw new IllegalStateException("assert or failure occurred after test has completed");
        }
        this.throwable = t;
        t.printStackTrace();
        this.thrownThread = Thread.currentThread();
        this.latch.countDown();
        if (t instanceof AssertionError) {
            throw (AssertionError) t;
        }
    }

    protected void clearThrown() {
        this.throwable = null;
    }

    protected void checkThread() {
        this.threadNames.put(Thread.currentThread().getName(), new Exception());
    }

    protected void assertTrue(final String message, final boolean condition) {
        checkThread();
        try {
            Assert.assertTrue(message, condition);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertFalse(final boolean condition) {
        checkThread();
        try {
            Assert.assertFalse(condition);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertSame(final String message, final Object expected, final Object actual) {
        checkThread();
        try {
            Assert.assertSame(message, expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final long expected, final long actual) {
        checkThread();
        try {
            Assert.assertEquals(expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNull(final Object object) {
        checkThread();
        try {
            Assert.assertNull(object);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertFalse(final String message, final boolean condition) {
        checkThread();
        try {
            Assert.assertFalse(message, condition);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void fail(final String message) {
        checkThread();
        try {
            Assert.fail(message);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNull(final String message, final Object object) {
        checkThread();
        try {
            Assert.assertNull(message, object);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    @Deprecated
    protected void assertEquals(final String message, final double expected, final double actual) {
        checkThread();
        try {
            Assert.assertEquals(message, expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }


    protected void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final short[] expecteds, final short[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final long[] expecteds, final long[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNotNull(final Object object) {
        checkThread();
        try {
            Assert.assertNotNull(object);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final Object expected, final Object actual) {
        checkThread();
        try {
            Assert.assertEquals(expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final String message, final Object expected, final Object actual) {
        checkThread();
        try {
            Assert.assertEquals(message, expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertTrue(final boolean condition) {
        checkThread();
        try {
            Assert.assertTrue(condition);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNotNull(final String message, final Object object) {
        checkThread();
        try {
            Assert.assertNotNull(message, object);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final String message, final double expected, final double actual, final double delta) {
        checkThread();
        try {
            Assert.assertEquals(message, expected, actual, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void fail() {
        checkThread();
        try {
            Assert.fail();
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void fail(final Throwable cause) {
        checkThread();
        handleThrowable(cause);
    }

    protected void assertSame(final Object expected, final Object actual) {
        checkThread();
        try {
            Assert.assertSame(expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final String message, final long expected, final long actual) {
        checkThread();
        try {
            Assert.assertEquals(message, expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertEquals(final double expected, final double actual, final double delta) {
        checkThread();
        try {
            Assert.assertEquals(expected, actual, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected <T> void assertThat(final T actual, final Matcher<T> matcher) {
        checkThread();
        try {
            Assert.assertThat(actual, matcher);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    @Deprecated
    protected void assertEquals(final String message, final Object[] expecteds, final Object[] actuals) {
        checkThread();
        try {
            Assert.assertEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    @Deprecated
    protected void assertEquals(final Object[] expecteds, final Object[] actuals) {
        checkThread();
        try {
            Assert.assertEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNotSame(final String message, final Object unexpected, final Object actual) {
        checkThread();
        try {
            Assert.assertNotSame(message, unexpected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected <T> void assertThat(final String reason, final T actual, final Matcher<T> matcher) {
        checkThread();
        try {
            Assert.assertThat(reason, actual, matcher);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertNotSame(final Object unexpected, final Object actual) {
        checkThread();
        try {
            Assert.assertNotSame(unexpected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final char[] expecteds, final char[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals, delta);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final int[] expecteds, final int[] actuals) {
        checkThread();
        try {
            Assert.assertArrayEquals(expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    @Deprecated
    protected void assertEquals(final double expected, final double actual) {
        checkThread();
        try {
            Assert.assertEquals(expected, actual);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
        checkThread();
        try {
            Assert.assertArrayEquals(message, expecteds, actuals);
        } catch (final AssertionError e) {
            handleThrowable(e);
        }
    }

    protected <T> Handler<AsyncResult<T>> onFailure(final Consumer<Throwable> consumer) {
        return result -> {
            assertFalse(result.succeeded());
            consumer.accept(result.cause());
        };
    }

    protected void awaitLatch(final CountDownLatch latch) throws InterruptedException {
        assertTrue(latch.await(10, TimeUnit.SECONDS));
    }

    protected void assertWaitUntil(final BooleanSupplier supplier) {
        assertWaitUntil(supplier, 10000);
    }

    protected void waitUntil(final BooleanSupplier supplier) {
        waitUntil(supplier, 10000);
    }

    protected void assertWaitUntil(final BooleanSupplier supplier, final long timeout) {
        if (!waitUntil(supplier, timeout)) {
            throw new IllegalStateException("Timed out");
        }
    }

    protected boolean waitUntil(final BooleanSupplier supplier, final long timeout) {
        final long start = System.currentTimeMillis();
        while (true) {
            if (supplier.getAsBoolean()) {
                return true;
            }
            try {
                Thread.sleep(10);
            } catch (final InterruptedException ignore) {
            }
            final long now = System.currentTimeMillis();
            if (now - start > timeout) {
                return false;
            }
        }
    }

    protected <T> Handler<AsyncResult<T>> onSuccess(final Consumer<T> consumer) {
        return result -> {
            if (result.failed()) {
                result.cause().printStackTrace();
                fail(result.cause().getMessage());
            } else {
                consumer.accept(result.result());
            }
        };
    }

    protected void close(final Vertx vertx) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        vertx.close(ar -> {
            latch.countDown();
        });
        awaitLatch(latch);
    }
}
