package ca.ulaval.glo4003.repUL.api.subscription.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SubscriptionAddCreditCardRequest(
        @NotEmpty
        String CardNumber,
        @NotNull
        int ccv,
        @NotNull
        LocalDate expDate,
        @NotEmpty
        String fullName
) {
}
