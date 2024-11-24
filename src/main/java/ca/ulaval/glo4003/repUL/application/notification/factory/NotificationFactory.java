package ca.ulaval.glo4003.repUL.application.notification.factory;

import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUserDto;
import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUsersDto;
import ca.ulaval.glo4003.repUL.domain.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFactory {
    public Notification toNotification(SendNotificationToUserDto sendNotificationToUserDto) {
        return new Notification(
                sendNotificationToUserDto.email(),
                sendNotificationToUserDto.message()
        );
    }

    public List<Notification> toNotifications(SendNotificationToUsersDto sendNotificationToUsersDto) {
        List<Notification> notifications = new ArrayList<>();

        for (String email: sendNotificationToUsersDto.emails()) {
            notifications.add(
                    new Notification(
                            email,
                            sendNotificationToUsersDto.message()
                    )
            );
        }
        return notifications;
    }
}
