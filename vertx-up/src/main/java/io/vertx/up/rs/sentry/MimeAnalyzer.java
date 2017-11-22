package io.vertx.up.rs.sentry;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.media.Analyzer;
import io.vertx.up.media.MediaAnalyzer;
import io.vertx.up.rs.Sentry;
import io.vertx.zero.tool.mirror.Instance;

/**
 * Media analyzer for current request
 */
public class MimeAnalyzer implements Sentry {
    private transient final Analyzer analyzer =
            Instance.singleton(MediaAnalyzer.class);

    @Override
    public Handler<RoutingContext> signal(final Depot depot) {
        return (context) -> {
            final Event reference = depot.getEvent();
            /** Build argument **/
            this.analyzer.in(context, reference);

            context.next();
        };
    }
}
