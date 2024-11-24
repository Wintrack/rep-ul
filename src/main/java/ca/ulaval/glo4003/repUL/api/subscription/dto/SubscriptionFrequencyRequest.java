package ca.ulaval.glo4003.repUL.api.subscription.dto;

import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SubscriptionFrequencyRequest(
        @NotNull
        SubscriptionFrequencyType frequencyType,
        @NotNull
        LocalDateTime date
) {
}
