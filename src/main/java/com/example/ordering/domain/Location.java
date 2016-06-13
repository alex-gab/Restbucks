package com.example.ordering.domain;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Location {
    @XmlEnumValue("takeaway")
    TAKEAWAY,
    @XmlEnumValue("inStore")
    IN_STORE
}
