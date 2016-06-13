package com.example.ordering.representations;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Optional;

import static com.example.ordering.common.Preconditions.checkNotNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Representation {
    public static final String RELATIONS_URI = "http://relations.restbucks.com/";
    public static final String RESTBUCKS_NAMESPACE = "http://schemas.restbucks.com";
    public static final String DAP_NAMESPACE = RESTBUCKS_NAMESPACE + "/dap";
    public static final String RESTBUCKS_MEDIA_TYPE = "application/vnd.restbucks+xml";
    public static final String SELF_REL_VALUE = "self";

    @XmlElement(name = "link", namespace = DAP_NAMESPACE)
    protected List<Link> links;

    protected final Optional<Link> getLinkByName(final String uriName) {
        checkNotNull(uriName, "uriName");

        if (links == null) {
            return empty();
        }

        for (final Link l : links) {
            if (l.getRelValue().toLowerCase().equals(uriName.toLowerCase())) {
                return of(l);
            }
        }

        return empty();
    }


}
