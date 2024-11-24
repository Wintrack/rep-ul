package ca.ulaval.glo4003.repUL.application.notification;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUserDto;
import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUsersDto;
import ca.ulaval.glo4003.repUL.application.notification.factory.NotificationFactory;
import ca.ulaval.glo4003.repUL.application.notification.infra.NotificationInfra;
import ca.ulaval.glo4003.repUL.domain.notification.Notification;

import java.util.List;

public class NotificationService {
    private NotificationInfra notificationInfra;

    private NotificationFactory notificationFactory;

    public NotificationService() {
        this.notificationInfra = ServiceLocator.getInstance().getService(NotificationInfra.class);
        this.notificationFactory = ServiceLocator.getInstance().getService(NotificationFactory.class);
    }

    public void sendNotificationToUser(SendNotificationToUserDto sendNotificationToUserDto) {
        Notification notification = notificationFactory.toNotification(sendNotificationToUserDto);

        notificationInfra.sendNotification(notification);
    }

    public void sendNotificationToUsers(SendNotificationToUsersDto sendNotificationToUsersDto) {
        List<Notification> notifications = notificationFactory.toNotifications(sendNotificationToUsersDto);

        notificationInfra.sendNotifications(notifications);
    }

    public List<Notification> getAllNotificationByUser(String email) {
        return notificationInfra.getAllNotificationByUser(email);
    }
}
