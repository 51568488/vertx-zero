package io.vertx.up.eon.em;

/**
 * Wall type for security
 */
public enum WallType {
    // Mongo Type
    MONGO("mongo"),
    // Defined
    CUSTOM("custom");

    private transient final String literal;

    WallType(final String literal) {
        this.literal = literal;
    }

    public String key() {
        return this.literal;
    }

    public boolean match(final String literal) {
        return null != literal && this.literal.equals(literal);
    }
}
