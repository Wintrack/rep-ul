package ca.ulaval.glo4003.repUL.infrastructure.notification;

import ca.ulaval.glo4003.repUL.application.notification.infra.NotificationInfra;
import ca.ulaval.glo4003.repUL.domain.notification.Notification;

import java.util.*;

public class NotificationInfraImpl implements NotificationInfra {
    private final Map<String, List<Notification>> notificationMap;

    public NotificationInfraImpl() {
        this.notificationMap = new HashMap<>();
    }

    @Override
    public void sendNotification(Notification notification) {
        notification.setId(UUID.randomUUID().toString());
        if (notificationMap.containsKey(notification.getEmail())) {
            List<Notification> notifications = notificationMap.get(notification.getEmail());

            notifications.add(notification);
        } else {
            List<Notification> notifications = new ArrayList<>();

            notifications.add(notification);
            notificationMap.put(notification.getEmail(), notifications);
        }
    }

    @Override
    public void sendNotifications(List<Notification> notifications) {
        notifications.forEach(this::sendNotification);
    }

    @Override
    public List<Notification> getAllNotificationByUser(String email) {
        if (notificationMap.containsKey(email)) {
            return notificationMap.get(email);
        }
        return List.of();
    }
}
