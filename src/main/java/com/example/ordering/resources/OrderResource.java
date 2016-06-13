package com.example.ordering.resources;

import com.example.ordering.activities.ReadOrderActivity;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/order")
public class OrderResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public final Response getOrder() {
        try {
            final OrderRepresentation responseRepresentation = new ReadOrderActivity().retrieveByUri(new RestbucksUri(uriInfo.getRequestUri()));
            return Response.ok().entity(responseRepresentation).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
