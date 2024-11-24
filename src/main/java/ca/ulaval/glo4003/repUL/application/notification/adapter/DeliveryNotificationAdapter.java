package ca.ulaval.glo4003.repUL.application.notification.adapter;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.notification.DeliveryNotification;
import ca.ulaval.glo4003.repUL.application.notification.NotificationService;
import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUsersDto;

import java.util.List;

public class DeliveryNotificationAdapter implements DeliveryNotification {
    private final NotificationService notificationService;

    public DeliveryNotificationAdapter() {
        this.notificationService = ServiceLocator.getInstance().getService(NotificationService.class);
    }

    @Override
    public void notifyCreatedToAllDeliverMan(List<String> emails, String id) {
        String message = "Delivery with " + id + " has been created";

        this.notificationService.sendNotificationToUsers(
                new SendNotificationToUsersDto(emails, message)
        );
    }
}
