package io.vertx.up.eon.em;

public enum ServerType {
    HTTP("http");

    private transient final String literal;

    ServerType(final String literal) {
        this.literal = literal;
    }

    public String key() {
        return this.literal;
    }

    public boolean match(final String literal) {
        if (null == literal) {
            return false;
        }
        return this.literal.equals(literal);
    }
}
