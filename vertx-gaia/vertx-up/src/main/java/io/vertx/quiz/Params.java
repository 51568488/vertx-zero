package io.vertx.quiz;

public class Params {

    private transient final Class<?> executor;

    public static Params start(final Class<?> executor) {
        return new Params(executor);
    }

    private Params(final Class<?> executor) {
        this.executor = executor;
        System.out.println("[Mok] Started ");
    }

    public Params monitor(final Object data) {
        System.out.println("[Mok] Data: " + ((null == data) ? null : data.toString()));
        return this;
    }

    public void end() {
        System.out.println("[Mok] Ended By: " + ((null == this.executor)? null: this.executor.getName()));
    }
}
