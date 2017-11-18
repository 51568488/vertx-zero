package org.di;

import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.atom.Envelop;

import javax.inject.infix.Mongo;


public class InjectDao {

    @Mongo
    private transient MongoClient client;

    public void async(final Envelop envelop) {
        System.out.println(this.client);
    }
}
