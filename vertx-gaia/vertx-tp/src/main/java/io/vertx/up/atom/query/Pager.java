package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

import java.io.Serializable;

public class Pager implements Serializable {

    private static final String PAGE = "page";
    private static final String SIZE = "size";
    /**
     * Start page: >= 1
     */
    private final transient int page;
    /**
     * Page size
     */
    private final transient int size;
    /**
     * From index: offset
     */
    private transient int start;
    /**
     * To index: limit
     */
    private transient int end;

    /**
     * Create pager by page, size
     *
     * @param page
     * @param size
     * @return
     */
    public static Pager create(final Integer page, final Integer size) {
        return new Pager(page, size);
    }

    /**
     * Another mode to create Pager
     *
     * @param pageJson
     * @return
     */
    public static Pager create(final JsonObject pageJson) {
        final int page = pageJson.getInteger(PAGE);
        final int size = pageJson.getInteger(SIZE);
        return new Pager(page, size);
    }

    private Pager(final Integer page,
                  final Integer size) {
        this.page = page;
        this.size = size;
        Fn.safeNull(() -> {
            // Caculate
            this.start = (page - 1) * size;
            this.end = page * size;
        }, page, size);
    }

    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(PAGE, this.page);
        data.put(SIZE, this.size);
        return data;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getTop() {
        return this.size;
    }
}
