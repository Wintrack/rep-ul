package ca.ulaval.glo4003.repUL.api.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.DeliveryManService;
import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryManDto;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("delivery/deliveryman")
public class DeliveryManResource {

    private final DeliveryManService deliveryManService;

    public DeliveryManResource() {
        this.deliveryManService = ServiceLocator.getInstance().getService(DeliveryManService.class);
    }

    @POST()
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            CreateDeliveryManDto createDeliveryManDto
    ) {
       deliveryManService.register(createDeliveryManDto);

        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

}
