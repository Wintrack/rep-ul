package ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient;

import jakarta.validation.constraints.NotEmpty;

public record CreateIngredientRequest(
        @NotEmpty
        String ingredient,
        @NotEmpty
        String quantity
) {
}
