package ca.ulaval.glo4003.repUL.application.subscription.event;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;

public record SubscriptionEvent(
        String subscriptionId,
        String getUserEmail,
        String getUserAddress,
        FoodBox getFoodBox,
        SubscriptionFrequency getFrequency
) {}
