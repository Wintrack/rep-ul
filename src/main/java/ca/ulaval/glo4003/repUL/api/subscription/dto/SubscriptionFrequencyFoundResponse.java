package ca.ulaval.glo4003.repUL.api.subscription.dto;

import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType;

public record SubscriptionFrequencyFoundResponse(
        SubscriptionFrequencyType frequencyType,
        String date
) {
}
