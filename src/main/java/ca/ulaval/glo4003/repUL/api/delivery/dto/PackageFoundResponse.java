package ca.ulaval.glo4003.repUL.api.delivery.dto;

import ca.ulaval.glo4003.repUL.domain.delivery.status.DeliveryStatus;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;

public record PackageFoundResponse(
        String id,
        String destination,
        int destinationCase,
        FoodBox foodBox,
        DeliveryStatus deliveryStatus
) {
}
