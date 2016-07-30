package com.example.ordering.repositories;

import com.example.ordering.domain.Identifier;
import com.example.ordering.domain.Order;

import java.util.HashMap;
import java.util.Map;

import static com.example.ordering.common.Preconditions.checkNotNull;

public final class OrderRepository {
    private static final OrderRepository repo = new OrderRepository();
    private Map<String, Order> backingStore = new HashMap<>();

    private OrderRepository() {
    }

    public static OrderRepository current() {
        return repo;
    }

    public final Order get(final Identifier id) {
        return backingStore.get(checkNotNull(id, "id").toString());
    }

    public final Order take(final Identifier id) {
        final Order order = backingStore.get(checkNotNull(id, "id").toString());
        remove(id);
        return order;
    }

    public final Identifier store(final Order order) {
        checkNotNull(order, "order");
        final Identifier id = new Identifier();
        backingStore.put(id.toString(), order);
        return id;
    }

    public final void store(final Identifier id, final Order order) {
        backingStore.put(checkNotNull(id, "id").toString(), checkNotNull(order, "order"));
    }

    public final boolean has(final Identifier id) {
        return backingStore.containsKey(checkNotNull(id, "id").toString());
    }

    public final void remove(final Identifier id) {
        backingStore.remove(checkNotNull(id, "id").toString());
    }

    public final boolean orderPlaced(final Identifier id) {
        return current().has(checkNotNull(id, "id"));
    }

    public final boolean orderNotPlaced(final Identifier id) {
        return !current().has(checkNotNull(id, "id"));
    }

    public final void clear() {
        backingStore = new HashMap<>();
    }

    public final int size() {
        return backingStore.size();
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, Order> entry : backingStore.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\t:\t");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
