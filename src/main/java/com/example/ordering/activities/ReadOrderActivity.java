package com.example.ordering.activities;

import com.example.ordering.domain.Identifier;
import com.example.ordering.domain.Order;
import com.example.ordering.repositories.OrderRepository;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static com.example.ordering.representations.OrderRepresentationHelper.createResponseOrderRepresentation;

public final class ReadOrderActivity {

    public final OrderRepresentation retrieveByUri(final RestbucksUri orderUri) {
        final Identifier id = checkNotNull(orderUri, "orderUri").getId();

        // take it from the repository
//        final Order order = new Order(
//                Location.TAKEAWAY,
//                Arrays.asList(
//                        new Item(Size.LARGE, Milk.SKIM, Drink.ESPRESSO),
//                        new Item(Size.SMALL, Milk.SEMI, Drink.FLAT_WHITE)));
        final Order order = OrderRepository.current().get(orderUri.getId());

        if (order == null) {
            throw new NoSuchOrderException();
        }

        return createResponseOrderRepresentation(order, orderUri);

    }

}
