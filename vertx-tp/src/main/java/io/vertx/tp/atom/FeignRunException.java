package io.vertx.tp.atom;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Feign Run exception for errors.
 */
public class FeignRunException extends Exception {
    @JsonProperty("code")
    private String code;
    @JsonProperty("info")
    private String info;
    @JsonProperty("message")
    private String message;

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
