package com.example.ordering.representations;

import com.example.ordering.activities.InvalidOrderException;
import com.example.ordering.domain.Item;
import com.example.ordering.domain.Location;
import com.example.ordering.domain.Order;
import com.example.ordering.domain.OrderStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public static OrderRepresentation fromXmlString(final String xmlRepresentation) {
        try {
            final JAXBContext context = JAXBContext.newInstance(OrderRepresentation.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (OrderRepresentation) unmarshaller.unmarshal(new ByteArrayInputStream(xmlRepresentation.getBytes()));
        } catch (JAXBException e) {
            throw new InvalidOrderException(e);
        }
    }

    public final Order getOrder() {
        return new Order(location, status, items);
    }

    public Link getUpdateLink() {
        final Optional<Link> optional = getLinkByName(RELATIONS_URI + "update");
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UnsupportedOperationException("This representation does not have an update link");
        }
    }
}
