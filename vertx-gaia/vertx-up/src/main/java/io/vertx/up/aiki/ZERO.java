package io.vertx.up.aiki;

interface Info {

    String MSG_FILTER = "[ ZERO ] ( Mongo -> findOne ) collection = {0}, filter = {1}, result = {2}.";
    String MSG_INSERT = "[ ZERO ] ( Mongo -> insert ) collection = {0}, data = {1}.";
    String MSG_UPDATE = "[ ZERO ] ( Mongo -> findOneAndReplace ) collection = {0}, filter = {1}, result = {2}.";
}
