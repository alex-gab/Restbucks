package com.example.ordering.common;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static java.lang.String.format;

public final class Exceptions {
    public static RuntimeException newRuntimeException(final String format, final String... messages) {
        return new RuntimeException(format(checkNotNull(format, "format"), messages));
    }
}
