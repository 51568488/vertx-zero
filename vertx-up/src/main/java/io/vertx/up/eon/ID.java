package io.vertx.up.eon;

public interface ID {

    String PARAMS = "$$PARAM$$";

    String DIRECT = "$$DIRECT$$";

    String IGNORE = "$$IGNORE$$";

    interface Page {

        String PAGE = "page";

        String SIZE = "size";
    }

    interface Header {

        String USER = "X-User";

        String ROLE = "X-Role";
    }
}
