package ca.ulaval.glo4003.repUL.api.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.OrderPreparerService;
import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderPreparerDto;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/order/preparer")
public class OrderPreparerResource {

    private final OrderPreparerService orderPreparerService;

    public OrderPreparerResource() {
        orderPreparerService = ServiceLocator.getInstance().getService(OrderPreparerService.class);
    }

    @POST()
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            CreateOrderPreparerDto createOrderPreparerDto
    ) {
        orderPreparerService.createOrderPreparer(createOrderPreparerDto);

        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }
}
