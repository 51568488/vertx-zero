package io.vertx.tp.atom;

import io.vertx.core.json.JsonObject;

/**
 * Feign Run exception for errors.
 */
public class FeignRunException extends Exception {

    private int code;
    private String info;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
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

    public JsonObject getResponse() {
        final JsonObject data = new JsonObject();
        data.put("code", this.code);
        data.put("info", this.info);
        data.put("message", this.message);
        return data;
    }
}
