package com.example.ordering.resources;

import com.example.ordering.activities.*;
import com.example.ordering.domain.Order;
import com.example.ordering.representations.OrderRepresentation;
import com.example.ordering.representations.RestbucksUri;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.Response.Status.*;

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
        } catch (final NoSuchOrderException nsoe) {
            return Response.status(NOT_FOUND).build();
        } catch (final Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes("application/vnd.restbucks+xml")
    @Produces("application/vnd.restbucks+xml")
    public final Response createOrder(final String orderRepresentation) {
        try {
            final Order order = OrderRepresentation.fromXmlString(orderRepresentation).getOrder();
            final OrderRepresentation responseRepresentation = new CreateOrderActivity().create(order, new RestbucksUri(uriInfo.getRequestUri()));

            return Response.created(responseRepresentation.getUpdateLink().getUri()).entity(responseRepresentation).build();
        } catch (InvalidOrderException ioe) {
            return Response.status(BAD_REQUEST).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public final Response removeOrder() {
        try {
            final OrderRepresentation removedOrder = new RemoveOrderActivity().delete(new RestbucksUri(uriInfo.getRequestUri()));
            return Response.ok().entity(removedOrder).build();
        } catch (final NoSuchOrderException nsoe) {
            return Response.status(NOT_FOUND).build();
        } catch (final OrderDeletionException ode) {
            return Response.status(405).header("Allow", "GET").build();
        } catch (final Exception e) {
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/{orderId}")
    @Consumes("application/vnd.restbucks+xml")
    @Produces("application/vnd.restbucks+xml")
    public final Response updateOrder(final String orderRepresentation) {
        try {
            final Order order = OrderRepresentation.fromXmlString(orderRepresentation).getOrder();
            final OrderRepresentation updateRepresentation = new UpdateOrderActivity().update(order, new RestbucksUri(uriInfo.getRequestUri()));
            return Response.ok().entity(updateRepresentation).build();
        } catch (final InvalidOrderException ioe) {
            return Response.status(BAD_REQUEST).build();
        } catch (final NoSuchOrderException nsoe) {
            return Response.status(NOT_FOUND).build();
        } catch (final UpdateException uex) {
            return Response.status(CONFLICT).build();
        } catch (final Exception ex) {
            return Response.serverError().build();
        }
    }
}
