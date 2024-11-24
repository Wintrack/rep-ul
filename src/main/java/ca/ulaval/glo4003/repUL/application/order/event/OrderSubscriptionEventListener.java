package ca.ulaval.glo4003.repUL.application.order.event;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.OrderService;
import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderDto;
import ca.ulaval.glo4003.repUL.application.order.factory.OrderDtoFactory;
import ca.ulaval.glo4003.repUL.application.order.factory.OrderFactory;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEvent;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventListener;
import ca.ulaval.glo4003.repUL.domain.order.Order;

public class OrderSubscriptionEventListener implements SubscriptionEventListener {
    private final OrderService orderService;

    private final OrderDtoFactory orderDtoFactory;

    public OrderSubscriptionEventListener() {
        orderService = ServiceLocator.getInstance().getService(OrderService.class);
        orderDtoFactory = ServiceLocator.getInstance().getService(OrderDtoFactory.class);
    }

    @Override
    public void receiveEvent(SubscriptionEvent subscriptionEvent) {
        CreateOrderDto createOrderDto = orderDtoFactory.toCreateOrderDto(subscriptionEvent);

        orderService.addOrderToWaitingList(createOrderDto);
    }
}
