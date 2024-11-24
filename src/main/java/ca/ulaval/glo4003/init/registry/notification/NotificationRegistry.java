package ca.ulaval.glo4003.init.registry.notification;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.notification.DeliveryNotification;
import ca.ulaval.glo4003.repUL.application.notification.NotificationService;
import ca.ulaval.glo4003.repUL.application.notification.adapter.DeliveryNotificationAdapter;
import ca.ulaval.glo4003.repUL.application.notification.infra.NotificationInfra;
import ca.ulaval.glo4003.repUL.infrastructure.notification.NotificationInfraImpl;

public class NotificationRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(NotificationInfra.class, new NotificationInfraImpl());
        serviceLocator.registerService(NotificationService.class, new NotificationService());
        serviceLocator.registerService(DeliveryNotification.class, new DeliveryNotificationAdapter());
    }
}
