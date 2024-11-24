package ca.ulaval.glo4003.repUL.application.notification.event;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.notification.NotificationService;
import ca.ulaval.glo4003.repUL.application.notification.dto.SendNotificationToUserDto;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEvent;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventListener;
import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

import static ca.ulaval.glo4003.repUL.domain.order.type.OrderType.TAKEAWAY;

public class NotificationOrderListener implements OrderEventListener {
    private final NotificationService notificationService;

    public NotificationOrderListener() {
        this.notificationService = ServiceLocator.getInstance().getService(NotificationService.class);
    }

    @Override
    public void receiveEvent(OrderEvent orderEvent) {
        List<Order> ordersTakeaway = orderEvent.orders()
                .stream().filter(order -> order.getOrderType() == TAKEAWAY)
                .toList();
        List<SendNotificationToUserDto> notifications = new ArrayList<>();

        for (Order order: ordersTakeaway) {
            notificationService.sendNotificationToUser(
                    new SendNotificationToUserDto(
                            order.getUserEmail(),
                            "Delivery " +
                                    order.getId() +
                                    " is ready to be picked up at " +
                                    order.getDestination()
                    )
            );
        }

    }
}
