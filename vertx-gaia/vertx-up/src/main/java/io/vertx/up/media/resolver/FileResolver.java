package io.vertx.up.media.resolver;

import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.media.Resolver;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.eon.Values;

import java.util.Set;

@SuppressWarnings("unchecked")
public class FileResolver<T> implements Resolver<T> {

    @Override
    public Epsilon<T> resolve(final RoutingContext context,
                              final Epsilon<T> income) {
        final Set<FileUpload> fileUploads = context.fileUploads();
        if (Values.ONE == fileUploads.size()) {
            final FileUpload fileUpload = fileUploads.iterator().next();
            // Returned directly reference for FileUpload
            if (income.getArgType().isAssignableFrom(FileUpload.class)) {
                income.setValue((T) fileUpload);
            } else {
                // Return other object to extract data from uploaded file.
                final Object ret = ZeroSerializer.getValue(income.getArgType(), fileUpload.uploadedFileName());
                income.setValue((T) ret);
            }
        } else {
            // Multi Files only support Set<FileUpload>
            income.setValue((T) fileUploads);
        }
        return income;
    }
}
