package ca.ulaval.glo4003.repUL.api.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.order.assembler.OrderDtoAssembler;
import ca.ulaval.glo4003.repUL.api.order.dto.*;
import ca.ulaval.glo4003.repUL.application.order.OrderService;
import ca.ulaval.glo4003.repUL.application.order.dto.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.ALL;
import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.PREPARER;

@Path("/order")
public class OrderResourceImpl {
    private final OrderService orderService;

    private final OrderDtoAssembler orderDtoAssembler;

    public OrderResourceImpl() {
        this.orderService = ServiceLocator.getInstance().getService(OrderService.class);
        this.orderDtoAssembler = ServiceLocator.getInstance().getService(OrderDtoAssembler.class);
    }

    @Protected(PREPARER)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrdersOfTheDay() {
        orderService.createOrdersOfTheDay();

        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(PREPARER)
    @PUT()
    @Path("/{id}:setReadyOrAssembled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setReadyOrAssembled(
            @Context SecurityContext securityContext,
            @PathParam("id") String id,
            SetReadyOrAssembledRequest setReadyOrAssembledRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();

        orderService.setOrderAssembledOrReady(
                new SetOrderAssembledOrReadyDto(
                        id,
                        setReadyOrAssembledRequest.orderStatus(),
                        email
                )
        );
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(PREPARER)
    @PUT()
    @Path("/setReady")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setReady(
            @Context SecurityContext securityContext,
            SelectOrdersRequest selectOrdersRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();

        orderService.setOrdersReady(
                new SetOrdersReadyDto(
                        selectOrdersRequest.orderIds(),
                        email
                )
        );
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(PREPARER)
    @PUT()
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectOrders(
            @Context SecurityContext securityContext,
            SelectOrdersRequest selectOrdersRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();

        orderService.selectOrders(
                new SelectOrderDto(
                        selectOrdersRequest.orderIds(),
                        email
                )
        );
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(PREPARER)
    @PUT()
    @Path("/confirm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmOrders(
            @Context SecurityContext securityContext,
            ConfirmOrdersRequest confirmOrdersRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();

        orderService.confirmOrders(
                new ConfirmOrdersDto(
                        confirmOrdersRequest.orderIds(),
                        email
                )
        );
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(PREPARER)
    @PUT()
    @Path("/abort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response abortsOrders(
            @Context SecurityContext securityContext,
            AbortOrdersRequest abortOrdersRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();

        orderService.abortOrders(
                new AbortOrderDto(
                        abortOrdersRequest.orderIds(),
                        email
                )
        );
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(ALL)
    @GET()
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserOrders(
            @Context SecurityContext securityContext
    ) {
        String email = securityContext.getUserPrincipal().getName();
        List<OrderGetResponse> orderGetResponses = orderService
                .getUserOrders(email)
                .stream().map(orderDtoAssembler::toOrderGetResponse)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(orderGetResponses)
                .build();
    }

    @Protected(PREPARER)
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        List<OrderGetResponse> orderGetResponses = orderService
                .getAllOrder()
                .stream().map(orderDtoAssembler::toOrderGetResponse)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(orderGetResponses)
                .build();
    }
}
