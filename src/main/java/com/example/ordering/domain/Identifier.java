package com.example.ordering.domain;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static java.util.UUID.randomUUID;

public final class Identifier {
    private final String identifier;

    public Identifier(final String identifier) {
        this.identifier = checkNotNull(identifier, "identifier");
    }

    public Identifier() {
        this(randomUUID().toString());
    }

    @Override
    public final String toString() {
        return identifier;
    }
}
