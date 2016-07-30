package com.example.ordering.activities;

import com.example.ordering.domain.Identifier;
import com.example.ordering.domain.Order;
import com.example.ordering.repositories.OrderRepository;
import com.example.ordering.representations.Link;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static com.example.ordering.domain.OrderStatus.UNPAID;
import static com.example.ordering.representations.Representation.RELATIONS_URI;
import static com.example.ordering.representations.Representation.SELF_REL_VALUE;

public final class CreateOrderActivity {

    public final OrderRepresentation create(final Order order, final RestbucksUri requestUri) {
        checkNotNull(order, "order");
        checkNotNull(requestUri, "requestUri");

        order.setStatus(UNPAID);
        final Identifier identifier = OrderRepository.current().store(order);

        final RestbucksUri orderUri = new RestbucksUri(requestUri.getBaseUri() + "/order/" + identifier.toString());
        final RestbucksUri paymentUri = new RestbucksUri(requestUri.getBaseUri() + "/payment/" + identifier.toString());

        return new OrderRepresentation(order,
                new Link(RELATIONS_URI + "cancel", orderUri),
                new Link(RELATIONS_URI + "payment", paymentUri),
                new Link(RELATIONS_URI + "update", orderUri),
                new Link(SELF_REL_VALUE, orderUri));
    }
}
