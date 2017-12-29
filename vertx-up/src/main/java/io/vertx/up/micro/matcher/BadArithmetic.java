package io.vertx.up.micro.matcher;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;

import java.util.List;

/**
 * Simple load balancer arithmetic
 */
public class BadArithmetic implements Arithmetic {
    @Override
    public Record search(final List<Record> records,
                         final RoutingContext context) {
        final HttpServerRequest request = context.request();
        System.out.println(request.uri());
        System.out.println(request.path());
        for (final Record record : records) {
            System.out.println(record.toJson());
        }
        return null;
    }
}
