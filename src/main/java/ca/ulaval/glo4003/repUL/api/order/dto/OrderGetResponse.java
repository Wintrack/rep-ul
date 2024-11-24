package ca.ulaval.glo4003.repUL.api.order.dto;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;
import ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus;

public record OrderGetResponse(
        String id,
        FoodBox foodBox,
        OrderStatus orderStatus,
        OrderPreparer orderPreparer
) {
}
