package ca.ulaval.glo4003.repUL.api.notification.assembler;

import ca.ulaval.glo4003.repUL.api.notification.dto.NotificationFoundResponse;
import ca.ulaval.glo4003.repUL.domain.notification.Notification;

public class NotificationDtoAssembler {
    public NotificationFoundResponse toNotificationFoundResponse(Notification notification) {
        return new NotificationFoundResponse(
                notification.getId(),
                notification.getMessage()
        );
    }
}
