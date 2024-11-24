package ca.ulaval.glo4003.repUL.api.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.subscription.assembler.SubscriptionAssembler;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionSporadicallyRequest;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionWeeklyRequest;
import ca.ulaval.glo4003.repUL.application.subscription.SubscriptionService;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSporadicSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.SubscriptionFoundDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.*;

@Path("/subscription")
@Protected
public class SubscriptionResourceImpl {

    private final SubscriptionAssembler subscriptionAssembler;

    private final SubscriptionService subscriptionService;

    public SubscriptionResourceImpl() {
        subscriptionService = ServiceLocator.getInstance().getService(SubscriptionService.class);
        subscriptionAssembler = ServiceLocator.getInstance().getService(SubscriptionAssembler.class);
    }

    @POST
    @Path("/weekly")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subscribeWeekly(
            @Context SecurityContext securityContext,
            @Valid SubscriptionWeeklyRequest userSubscriptionWeeklyRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();
        CreateSubscriptionDto createSubscriptionDto = subscriptionAssembler.toSubscription(
                email,
                userSubscriptionWeeklyRequest
        );

        subscriptionService.subscribeWeekly(createSubscriptionDto);
        return Response.status(CREATED).build();
    }

    @POST
    @Path("/sporadically")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subscribeSporadically(
            @Context SecurityContext securityContext,
            @Valid SubscriptionSporadicallyRequest subscriptionSporadicallyRequest
    ) {
        String email = securityContext.getUserPrincipal().getName();
        CreateSporadicSubscriptionDto subscription = subscriptionAssembler.toSubscription(
                email,
                subscriptionSporadicallyRequest
        );

        subscriptionService.subscribeSporadically(subscription);
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("/{id}:accept")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptFoodBox(
            @Context SecurityContext securityContext,
            @PathParam("id") String id
    ) {
        String email = securityContext.getUserPrincipal().getName();

        subscriptionService.acceptFoodBox(email, id);
        return Response.status(NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}:refuse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refuseSubscription(
            @Context SecurityContext securityContext,
            @PathParam("id") String id
    ) {
        String email = securityContext.getUserPrincipal().getName();

        subscriptionService.refuseSubscription(email, id);
        return Response.status(NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriptionsByUserId(@Context SecurityContext securityContext) {
        String userEmail = securityContext.getUserPrincipal().getName();
        List<SubscriptionFoundDto> subscriptionFoundResponses = subscriptionService
                .findSubscriptionByUserEmail(userEmail);

        return Response.status(OK).entity(subscriptionFoundResponses).build();
    }
}
