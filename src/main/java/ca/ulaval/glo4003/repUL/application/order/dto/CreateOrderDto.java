package ca.ulaval.glo4003.repUL.application.order.dto;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.order.type.OrderType;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;

import java.time.LocalDateTime;

public record CreateOrderDto(
        String userEmail,
        String destination,
        FoodBox foodBox,
        LocalDateTime deliveryDateTime,
        OrderType orderType
) {}
