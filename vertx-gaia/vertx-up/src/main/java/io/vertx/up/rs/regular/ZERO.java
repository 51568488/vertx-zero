package io.vertx.up.rs.regular;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<String, Ruler> RULERS = new ConcurrentHashMap<String, Ruler>() {
        {
            put("required", new RequiredRuler());
            put("length", new LengthRuler());
            put("minlength", new MinLengthRuler());
            put("maxlength", new MaxLengthRuler());
            put("empty", new EmptyRuler());
            put("singlefile", new SingleFileRuler());
        }
    };
}

interface Info {

    String MSG_FAILURE = "[ ZERO ] Rule validation failure: {0}";
}
