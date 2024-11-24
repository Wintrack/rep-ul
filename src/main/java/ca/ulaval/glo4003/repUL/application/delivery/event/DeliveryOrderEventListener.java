package ca.ulaval.glo4003.repUL.application.delivery.event;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.DeliveryService;
import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryDto;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEvent;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventListener;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;
import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.List;

import static ca.ulaval.glo4003.repUL.domain.order.type.OrderType.TO_DELIVER;

public class DeliveryOrderEventListener implements OrderEventListener {
    private static final String DEFAULT_LOCATION = "DESJARDINS";

    private final DeliveryService deliveryService;

    public DeliveryOrderEventListener() {
        this.deliveryService = ServiceLocator.getInstance().getService(DeliveryService.class);
    }

    @Override
    public void receiveEvent(OrderEvent orderEvent) {
        List<Order> ordersToDeliver = orderEvent.orders()
                .stream().filter(order -> order.getOrderType() == TO_DELIVER)
                .toList();
        List<Package> packages = ordersToDeliver
                .stream()
                .map(order -> new Package(
                        order.getDestination(),
                        order.getFoodBox(),
                        order.getUserEmail())
                ).toList();

        deliveryService.createDelivery(new CreateDeliveryDto(DEFAULT_LOCATION, packages));
    }
}
