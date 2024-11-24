package ca.ulaval.glo4003.repUL.application.notification.infra;

import ca.ulaval.glo4003.repUL.domain.notification.Notification;

import java.util.List;

public interface NotificationInfra {
    void sendNotification(Notification notification);

    void sendNotifications(List<Notification> notifications);

    List<Notification> getAllNotificationByUser(String email);
}
