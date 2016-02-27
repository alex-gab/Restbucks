package com.example.ordering.representations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import static com.example.ordering.common.Preconditions.checkNotNull;

@XmlRootElement(namespace = Representation.DAP_NAMESPACE)
public final class Link {
    @XmlAttribute(name = "rel")
    private String rel;
    @XmlAttribute(name = "uri")
    private String uri;
    @XmlAttribute(name = "mediaType")
    private String mediaType;

    /**
     * For JAXB
     */
    private Link() {
    }

    public Link(final String rel, final String uri, final String mediaType) {
        this.rel = checkNotNull(rel, "rel");
        this.uri = checkNotNull(uri, "uri");
        this.mediaType = checkNotNull(mediaType, "mediaType");
    }
}
