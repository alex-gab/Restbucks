package com.example.ordering.representations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;

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

    public Link(final String name, final RestbucksUri uri, final String mediaType) {
        this.rel = checkNotNull(name, "name");
        this.uri = checkNotNull(uri, "uri").getFullUri().toString();
        this.mediaType = checkNotNull(mediaType, "mediaType");
    }

    public Link(final String rel, final RestbucksUri uri) {
        this(rel, uri, Representation.RESTBUCKS_MEDIA_TYPE);
    }

    public String getRelValue() {
        return rel;
    }

    public URI getUri() {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMediaType() {
        return mediaType;
    }
}
