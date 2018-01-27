package io.vertx.up.aiki;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String MSG_FILTER = "[ ZERO ] ( Mongo -> findOne ) collection = {0}, filter = {1}, result = {2}.";
    String MSG_INSERT = "[ ZERO ] ( Mongo -> insert ) collection = {0}, data = {1}.";
    String MSG_UPDATE = "[ ZERO ] ( Mongo -> findOneAndReplace ) collection = {0}, filter = {1}, result = {2}.";
    String MSG_DELETE = "[ ZERO ] ( Mongo -> removeDocument ) Effected: {2} Rows. collection = {0}, filter = {1}";
    String MSG_FIND = "[ ZERO ] ( Mongo -> findWithOptions ) collection = {0}, filter = {1}, options = {2}, result = {3}";

    String RPC_RESULT = "[ ZERO ] ( Rpc -> thenRpc ) Ipc ( {0},{1} ) with params {2}, response data is {3}.";
    String JOOQ_PARSE = "[ ZERO ] ( Jooq -> fetchAndAsync ) Parsed result is field = {0}, key = {1}, value = {2}";
}

interface Pool {

    ConcurrentMap<Class<?>, UxJooq> JOOQ = new ConcurrentHashMap<>();
}
