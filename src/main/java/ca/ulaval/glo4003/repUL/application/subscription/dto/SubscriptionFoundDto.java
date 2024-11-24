package ca.ulaval.glo4003.repUL.application.subscription.dto;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionStatus;

public record SubscriptionFoundDto(
        String id,
        SubscriptionFrequencyFound frequency,
        String location,
        FoodBox foodBox,
        String startDate,
        SubscriptionStatus foodBoxAccepted
) {}
