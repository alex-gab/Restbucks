package com.example.ordering.activities;

import com.example.ordering.domain.Identifier;
import com.example.ordering.domain.Order;
import com.example.ordering.domain.OrderStatus;
import com.example.ordering.repositories.OrderRepository;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.OrderRepresentationHelper;
import com.example.ordering.representations.RestbucksUri;

public final class UpdateOrderActivity {
    public final OrderRepresentation update(final Order order, final RestbucksUri uri) {
        final Identifier id = uri.getId();

        final OrderRepository repo = OrderRepository.current();
        if (repo.orderNotPlaced(id)) {
            throw new NoSuchOrderException();
        }

        final Order storedOrder = repo.get(id);
        if (cannotBeChanged(storedOrder)) {
            throw new UpdateException();
        }

        storedOrder.setStatus(storedOrder.getStatus());
        storedOrder.calculateCost();

        return OrderRepresentationHelper.createResponseOrderRepresentation(storedOrder, uri);
    }

    private boolean cannotBeChanged(Order storedOrder) {
        return !(storedOrder.getStatus() == OrderStatus.UNPAID);
    }
}
