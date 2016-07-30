package com.example.ordering.activities;

import com.example.ordering.domain.Identifier;
import com.example.ordering.domain.Order;
import com.example.ordering.domain.OrderStatus;
import com.example.ordering.repositories.OrderRepository;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import static com.example.ordering.common.Preconditions.checkNotNull;

public final class RemoveOrderActivity {
    public final OrderRepresentation delete(final RestbucksUri uri) {
        final Identifier id = checkNotNull(uri, "uri").getId();

        final OrderRepository repo = OrderRepository.current();

        if (repo.orderNotPlaced(id)) {
            throw new NoSuchOrderException();
        }
        final Order order = repo.get(id);
        final OrderStatus orderStatus = order.getStatus();

        if (orderStatus == OrderStatus.PREPARING || orderStatus == OrderStatus.READY) {
            throw new OrderDeletionException();
        }

        if (orderStatus == OrderStatus.UNPAID) {
            repo.remove(id);
        }

        return new OrderRepresentation(order);
    }
}
