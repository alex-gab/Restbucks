package com.example.ordering.common;

import static java.lang.String.format;

public final class Preconditions {
    public static <T> T checkNotNull(final T reference, final String argumentName) {
        return jersey.repackaged.com.google.common.base.Preconditions.checkNotNull(reference, format("The value for argument '%s' cannot be null.", argumentName));
    }
}
