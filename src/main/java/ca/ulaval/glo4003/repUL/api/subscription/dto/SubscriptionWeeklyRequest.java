package ca.ulaval.glo4003.repUL.api.subscription.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SubscriptionWeeklyRequest(
        @Valid
        @NotNull
        SubscriptionAddCreditCardRequest creditCard,
        @NotNull
        String foodBoxId,
        @NotNull
        LocalDateTime frequency,
        @NotNull
        String location
) {
}
