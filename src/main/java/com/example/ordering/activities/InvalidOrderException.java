package com.example.ordering.activities;

import javax.xml.bind.JAXBException;

public final class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(final JAXBException cause) {
        super(cause);
    }
}
