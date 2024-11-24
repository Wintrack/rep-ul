package ca.ulaval.glo4003.repUL.api.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;
import ca.ulaval.glo4003.repUL.application.delivery.DeliveryService;
import ca.ulaval.glo4003.repUL.application.delivery.dto.AbortDeliveryDto;
import ca.ulaval.glo4003.repUL.application.delivery.dto.DeliveryFoundDto;
import ca.ulaval.glo4003.repUL.application.delivery.dto.OnGoingDeliveryDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/delivery")
public class DeliveryResource {
    private final DeliveryService deliveryService;

    public DeliveryResource() {
        this.deliveryService = ServiceLocator.getInstance().getService(DeliveryService.class);
    }

    @Protected(AuthorizationType.DELIVER)
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDelivery() {
        List<DeliveryFoundDto> allDelivery = deliveryService.findUserDelivery();

        return Response.status(Response.Status.OK)
                .entity(allDelivery)
                .build();
    }

    @Protected
    @Path("/user")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDelivery(@Context SecurityContext securityContext) {
        String userEmail = securityContext.getUserPrincipal().getName();
        List<DeliveryFoundDto> usersDelivery = deliveryService.findUserDelivery(userEmail);

        return Response.status(Response.Status.OK)
                .entity(usersDelivery)
                .build();
    }

    @Protected(AuthorizationType.DELIVER)
    @PUT
    @Path("{id}:setOnGoing")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setOnGoingDelivery(
            @Context SecurityContext securityContext,
            @PathParam("id") String id
    ) {
        String userEmail = securityContext.getUserPrincipal().getName();

        deliveryService.setOnGoingDelivery(new OnGoingDeliveryDto(id, userEmail));
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @Protected(AuthorizationType.DELIVER)
    @PUT
    @Path("{id}/package/{packageId}:abort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setAbortDelivery(
            @Context SecurityContext securityContext,
            @PathParam("id") String id,
            @PathParam("packageId") String packageId

    ) {
        String userEmail = securityContext.getUserPrincipal().getName();

        deliveryService.setAbortDelivery(new AbortDeliveryDto(id, packageId, userEmail));
        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }
}
