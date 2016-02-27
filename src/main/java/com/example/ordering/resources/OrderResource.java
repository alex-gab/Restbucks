package com.example.ordering.resources;

import com.example.ordering.representations.Link;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/order")
public class OrderResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public final Response getOrder() {
        final URI uri = uriInfo.getRequestUri();
        final Link link = new Link("someRel", "someUri", "someMediaType");
        return Response.ok().entity(link).build();
    }
}
