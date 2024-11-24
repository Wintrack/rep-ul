package ca.ulaval.glo4003.repUL.api.notification;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.notification.assembler.NotificationDtoAssembler;
import ca.ulaval.glo4003.repUL.api.notification.dto.NotificationFoundResponse;
import ca.ulaval.glo4003.repUL.application.notification.NotificationService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/notification")
@Protected
public class NotificationResourceImpl {
    private final NotificationDtoAssembler notificationDtoAssembler;

    private final NotificationService notificationService;

    public NotificationResourceImpl() {
        this.notificationDtoAssembler = ServiceLocator.getInstance().getService(NotificationDtoAssembler.class);
        this.notificationService = ServiceLocator.getInstance().getService(NotificationService.class);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotifications(
            @Context SecurityContext securityContext
    ) {
        String email = securityContext.getUserPrincipal().getName();
        List<NotificationFoundResponse> allNotificationByUser = notificationService
                .getAllNotificationByUser(email)
                .stream().map(notificationDtoAssembler::toNotificationFoundResponse)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(allNotificationByUser)
                .build();
    }
}
