package ca.ulaval.glo4003.repUL.api.subscription.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SubscriptionSporadicallyRequest(
        @Valid
        @NotNull
        SubscriptionAddCreditCardRequest creditCard,
        @NotNull
        String foodBoxId,
        @NotNull
        String location
) {
}
