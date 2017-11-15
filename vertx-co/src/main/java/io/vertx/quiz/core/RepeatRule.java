package io.vertx.quiz.core;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RepeatRule implements TestRule {
    private static class RepeatStatement extends Statement {

        private final int times;
        private final Statement statement;

        private RepeatStatement(final int times, final Statement statement) {
            this.times = times;
            this.statement = statement;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < this.times; i++) {
                System.out.println("*** Iteration " + (i + 1) + "/" + this.times + " of test");
                this.statement.evaluate();
            }
        }
    }

    @Override
    public Statement apply(final Statement statement, final Description description) {
        Statement result = statement;
        final Repeat repeat = description.getAnnotation(Repeat.class);
        if (repeat != null) {
            final int times = repeat.times();
            result = new RepeatStatement(times, statement);
        }
        return result;
    }
}
