package com.example.ordering.representations;

import com.example.ordering.activities.UriExchange;
import com.example.ordering.domain.Item;
import com.example.ordering.domain.Location;
import com.example.ordering.domain.Order;
import com.example.ordering.domain.OrderStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static com.example.ordering.domain.OrderStatus.*;
import static com.example.ordering.representations.Representation.RESTBUCKS_NAMESPACE;

@XmlRootElement(name = "order", namespace = RESTBUCKS_NAMESPACE)
public final class OrderRepresentation extends Representation {
    @XmlElement(name = "item", namespace = Representation.RESTBUCKS_NAMESPACE)
    private List<Item> items;
    @XmlElement(name = "location", namespace = Representation.RESTBUCKS_NAMESPACE)
    private Location location;
    @XmlElement(name = "cost", namespace = Representation.RESTBUCKS_NAMESPACE)
    private double cost;
    @XmlElement(name = "status", namespace = Representation.RESTBUCKS_NAMESPACE)
    private OrderStatus status;

    public OrderRepresentation() {
    }

    public OrderRepresentation(final Order order, Link... links) {
        this.location = order.getLocation();
        this.items = order.getItems();
        this.cost = order.calculateCost();
        this.status = order.getStatus();
        this.links = Arrays.asList(links);
    }

    public static OrderRepresentation createResponseOrderRepresentation(final Order order, final RestbucksUri orderUri) {
        checkNotNull(order, "order");
        checkNotNull(orderUri, "orderUri");

        final RestbucksUri paymentUri = new RestbucksUri(orderUri.getBaseUri() + "/payment/" + orderUri.getId().toString());

        final OrderStatus status = order.getStatus();
        if (status == UNPAID) {
            return new OrderRepresentation(order,
                    new Link(RELATIONS_URI + "cancel", orderUri),
                    new Link(RELATIONS_URI + "payment", paymentUri),
                    new Link(RELATIONS_URI + "update", orderUri),
                    new Link(SELF_REL_VALUE, orderUri));
        } else if (status == PREPARING) {
            return new OrderRepresentation(order, new Link(SELF_REL_VALUE, orderUri));
        } else if (status == READY) {
            return new OrderRepresentation(order, new Link(RELATIONS_URI + "receipt", UriExchange.receiptForPayment(paymentUri)));
        } else if (status == TAKEN) {
            return new OrderRepresentation(order);
        } else {
            throw new RuntimeException("Unknown Order Status");
        }
    }
}
