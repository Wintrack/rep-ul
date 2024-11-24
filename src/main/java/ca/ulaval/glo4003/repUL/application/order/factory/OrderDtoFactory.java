package ca.ulaval.glo4003.repUL.application.order.factory;

import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderDto;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEvent;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.type.OrderType;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType.WEEKLY;

public class OrderDtoFactory {
    public CreateOrderDto toCreateOrderDto(SubscriptionEvent subscriptionEvent) {
        OrderType orderType = null;

        if (subscriptionEvent.getFrequency().getFrequencyType() == WEEKLY) {
            orderType = OrderType.TO_DELIVER;
        } else {
            orderType = OrderType.TAKEAWAY;
        }

        return new CreateOrderDto(
                subscriptionEvent.getUserEmail(),
                subscriptionEvent.getUserAddress(),
                subscriptionEvent.getFoodBox(),
                subscriptionEvent.getFrequency().getDate(),
                orderType
        );
    }
}
