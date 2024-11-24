package ca.ulaval.glo4003.init.registry.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.event.DeliveryOrderEventListener;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryManStorage;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryStorage;
import ca.ulaval.glo4003.repUL.application.notification.event.NotificationOrderListener;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventSubscriber;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventType;
import ca.ulaval.glo4003.repUL.application.vendorLocation.adapter.DeliveryCaseAssignerAdapter;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.case_assigner.CaseAssigner;
import ca.ulaval.glo4003.repUL.infrastructure.delivery.DeliveryManStorageImpl;
import ca.ulaval.glo4003.repUL.infrastructure.delivery.DeliveryStorageImpl;

public class DeliveryRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(DeliveryStorage.class, new DeliveryStorageImpl());
        serviceLocator.registerService(DeliveryManStorage.class, new DeliveryManStorageImpl());
        serviceLocator.registerService(CaseAssigner.class, new DeliveryCaseAssignerAdapter());
        registerEventsListeners(serviceLocator);
    }

    private void registerEventsListeners(ServiceLocator serviceLocator) {
        OrderEventSubscriber service = serviceLocator.getService(OrderEventSubscriber.class);

        service.subscribe(OrderEventType.ORDER_ASSEMBLED, new DeliveryOrderEventListener());
        service.subscribe(OrderEventType.ORDER_ASSEMBLED, new NotificationOrderListener());
    }
}
