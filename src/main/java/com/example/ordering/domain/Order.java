package com.example.ordering.domain;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

import static com.example.ordering.common.Preconditions.checkNotNull;


public final class Order {
    private final Location location;
    private final List<Item> items;
    @XmlTransient
    private OrderStatus status = OrderStatus.UNPAID;

    public Order(final Location location, final List<Item> items) {
        this.location = checkNotNull(location, "location");
        this.items = checkNotNull(items, "items");
    }

    public final double calculateCost() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getDrink().getPrice();
        }
        return total;
    }

    public final Location getLocation() {
        return location;
    }

    public final List<Item> getItems() {
        return items;
    }

    public final OrderStatus getStatus() {
        return status;
    }
}
