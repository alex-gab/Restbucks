package com.example.ordering.domain;

import com.example.ordering.representations.Representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
    @XmlElement(namespace = Representation.RESTBUCKS_NAMESPACE)
    private Milk milk;
    @XmlElement(namespace = Representation.RESTBUCKS_NAMESPACE)
    private Size size;
    @XmlElement(namespace = Representation.RESTBUCKS_NAMESPACE)
    private Drink drink;

    /**
     * For JAXB :-(
     */
    Item() {
    }

    public Item(Size size, Milk milk, Drink drink) {
        this.milk = milk;
        this.size = size;
        this.drink = drink;
    }

    public Milk getMilk() {
        return milk;
    }

    public Size getSize() {
        return size;
    }

    public Drink getDrink() {
        return drink;
    }

    public String toString() {
        return size + " " + milk + " " + drink;
    }
}
