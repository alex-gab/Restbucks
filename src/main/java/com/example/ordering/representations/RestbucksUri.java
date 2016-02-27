package com.example.ordering.representations;

import com.example.ordering.domain.Identifier;

import java.net.URI;
import java.net.URISyntaxException;

import static com.example.ordering.common.Exceptions.newRuntimeException;
import static com.example.ordering.common.Preconditions.checkNotNull;

public final class RestbucksUri {
    private final URI uri;

    public RestbucksUri(final String uri) {
        try {
            this.uri = new URI(uri);
        } catch (final URISyntaxException e) {
            throw newRuntimeException("This is not a valid uri: %s. Exception: %s.", uri, e.getMessage());
        }
    }

    public RestbucksUri(final URI uri) {
        this(checkNotNull(uri, "uri").toString());
    }

    public final Identifier getId() {
        final String path = uri.getPath();
        return new Identifier(path.substring(path.lastIndexOf("/") + 1, path.length()));
    }
}
