package ca.ulaval.glo4003.repUL.api.subscription.dto;

import jakarta.validation.constraints.NotNull;

public record UserAddFoodBoxSubscription(
        @NotNull
        String foodBoxId
) {
}
