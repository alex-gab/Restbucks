package com.example.ordering.domain;

import javax.xml.bind.annotation.XmlEnumValue;

public enum OrderStatus {
    @XmlEnumValue("unpaid")
    UNPAID,
    @XmlEnumValue("preparing")
    PREPARING,
    @XmlEnumValue("ready")
    READY,
    @XmlEnumValue("taken")
    TAKEN
}
