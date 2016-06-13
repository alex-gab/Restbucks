package com.example.ordering.activities;

import com.example.ordering.domain.*;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import java.util.Arrays;

public final class ReadOrderActivity {
    public final OrderRepresentation retrieveByUri(final RestbucksUri orderUri) {
        final Identifier id = orderUri.getId();

        final Order order = new Order(
                Location.TAKEAWAY,
                Arrays.asList(
                        new Item(Size.LARGE, Milk.SKIM, Drink.ESPRESSO),
                        new Item(Size.SMALL, Milk.SEMI, Drink.FLAT_WHITE)));

        return OrderRepresentation.createResponseOrderRepresentation(order, orderUri);

    }
}
