package ca.ulaval.glo4003.repUL.application.subscription.dto;

import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType;

public record SubscriptionFrequencyFound(
        SubscriptionFrequencyType frequencyType,
        String date
) {}
