package io.vertx.up.media.resolver;

import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.exception.WebException;
import io.vertx.up.media.Resolver;

import java.util.Set;

public class BufferResolver<T> implements Resolver<T> {

    @Override
    public Epsilon<T> resolve(final RoutingContext context,
                              final Epsilon<T> income)
            throws WebException {
        System.out.println(income);
        // Buffer Resolver
        final Set<FileUpload> fileUploads = context.fileUploads();
        for (final FileUpload upload : fileUploads) {
            System.out.println(upload.charSet());
            System.out.println(upload.contentTransferEncoding());
            System.out.println(upload.contentType());
            System.out.println(upload.fileName());
            System.out.println(upload.name());
            System.out.println(upload.size());
            System.out.println(upload.uploadedFileName());
        }
        return income;
    }
}
