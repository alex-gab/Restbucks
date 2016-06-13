package com.example.ordering.domain;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Size {
    @XmlEnumValue(value = "small")
    SMALL,
    @XmlEnumValue(value = "medium")
    MEDIUM,
    @XmlEnumValue(value = "large")
    LARGE
}
