package io.vertx.up.media;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._415MediaNotSupportException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class MediaAnalyzer implements Analyzer {

    private static final Annal LOGGER = Annal.get(MediaAnalyzer.class);

    @Override
    public Object[] in(final RoutingContext context,
                       final Event event) throws WebException {
        /** Consume mime type matching **/
        final MediaType requestMedia = getMedia(context, HttpHeaders.CONTENT_TYPE);
        this.checkConsume(event, requestMedia);
        /** Selector execute for Analyzer selection **/
        
        return new Object[0];
    }

    @Override
    public Envelop out(final Envelop envelop,
                       final Event event) throws WebException {
        return null;
    }

    private MediaType getMedia(final RoutingContext context,
                               final CharSequence headerKey) {
        return MediaType.valueOf(context.request().getHeader(headerKey));
    }

    private void checkConsume(final Event event,
                              final MediaType type) throws WebException {
        final Set<MediaType> medias = event.getConsumes();
        if (!medias.contains(MediaType.WILDCARD_TYPE)) {
            /** 1. Start to parsing expected type **/
            boolean match = medias.stream()
                    .anyMatch(
                            media -> MediaType.MEDIA_TYPE_WILDCARD.equals(media.getType()) ||
                                    media.getType().equalsIgnoreCase(type.getType()));
            /** 2. Type checking **/
            Fn.flingUp(!match, LOGGER,
                    _415MediaNotSupportException.class,
                    getClass(), type, medias);
            /** 3. Start to parsing expected sub type **/
            match = medias.stream()
                    .anyMatch(
                            media -> MediaType.MEDIA_TYPE_WILDCARD.equals(media.getSubtype()) ||
                                    media.getSubtype().equalsIgnoreCase(type.getSubtype())
                    );
            /** 4. Subtype checking **/
            Fn.flingUp(!match, LOGGER,
                    _415MediaNotSupportException.class,
                    getClass(), type, medias);
        }
    }
}
