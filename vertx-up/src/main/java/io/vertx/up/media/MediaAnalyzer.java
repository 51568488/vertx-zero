package io.vertx.up.media;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.media.parse.EpsilonIncome;
import io.vertx.up.media.parse.Income;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SuppressWarnings("unchecked")
public class MediaAnalyzer implements Analyzer {

    private static final Annal LOGGER = Annal.get(MediaAnalyzer.class);

    private final transient Income<List<Epsilon<Object>>> income =
            Instance.singleton(EpsilonIncome.class);

    @Override
    public Object[] in(final RoutingContext context,
                       final Event event)
            throws WebException {
        /** Consume mime type matching **/
        final MediaType requestMedia = getMedia(context);
        MediaAtom.accept(event, requestMedia);

        /** Extract definition from method **/
        final List<Epsilon<Object>> epsilons =
                this.income.in(context, event);

        /** Extract value list **/
        return epsilons.stream()
                .map(Epsilon::getValue).toArray();
    }

    @Override
    public Envelop out(final Envelop envelop,
                       final Event event) throws WebException {
        // TODO: Replier
        return null;
    }

    private MediaType getMedia(final RoutingContext context) {
        final String header = context.request().getHeader(HttpHeaders.CONTENT_TYPE);
        return Fn.getSemi(StringUtil.isNil(header), LOGGER,
                () -> MediaType.WILDCARD_TYPE,
                () -> MediaType.valueOf(header));
    }

}
