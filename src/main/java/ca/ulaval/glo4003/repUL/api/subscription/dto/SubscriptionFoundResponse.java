package ca.ulaval.glo4003.repUL.api.subscription.dto;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionStatus;

public record SubscriptionFoundResponse(
        String id,
        SubscriptionFrequencyFoundResponse frequency,
        String location,
        FoodBox foodBox,
        String startDate,
        SubscriptionStatus foodBoxAccepted
) {
}
