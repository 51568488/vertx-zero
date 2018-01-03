package io.vertx.up.secure;

import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.plugin.mongo.MongoWall;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.Transformer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Wall {

    String TYPE = "type";
}

interface Pool {
    ConcurrentMap<WallType, Transformer<Cliff>>
            WALL_TRANSFORMER = new ConcurrentHashMap<WallType, Transformer<Cliff>>() {
        {
            put(WallType.MONGO, Instance.singleton(MongoWall.class));
        }
    };
}