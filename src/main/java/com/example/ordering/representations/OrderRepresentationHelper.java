package com.example.ordering.representations;

import com.example.ordering.activities.UriExchange;
import com.example.ordering.domain.Order;
import com.example.ordering.domain.OrderStatus;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static com.example.ordering.domain.OrderStatus.*;

public final class OrderRepresentationHelper {
    public static OrderRepresentation createResponseOrderRepresentation(final Order order, final RestbucksUri orderUri) {
        checkNotNull(order, "order");
        checkNotNull(orderUri, "orderUri");
        final RestbucksUri paymentUri = new RestbucksUri(orderUri.getBaseUri() + "/payment/" + orderUri.getId().toString());

        final OrderStatus status = order.getStatus();
        if (status == UNPAID) {
            return new OrderRepresentation(order,
                    new Link(Representation.RELATIONS_URI + "cancel", orderUri),
                    new Link(Representation.RELATIONS_URI + "payment", paymentUri),
                    new Link(Representation.RELATIONS_URI + "update", orderUri),
                    new Link(Representation.SELF_REL_VALUE, orderUri));
        } else if (status == PREPARING) {
            return new OrderRepresentation(order, new Link(Representation.SELF_REL_VALUE, orderUri));
        } else if (status == READY) {
            return new OrderRepresentation(order, new Link(Representation.RELATIONS_URI + "receipt", UriExchange.receiptForPayment(paymentUri)));
        } else if (status == TAKEN) {
            return new OrderRepresentation(order);
        } else {
            throw new RuntimeException("Unknown Order Status");
        }
    }
}
