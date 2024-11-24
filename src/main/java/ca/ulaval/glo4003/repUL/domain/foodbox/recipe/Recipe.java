package ca.ulaval.glo4003.repUL.domain.foodbox.recipe;

import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;

import java.util.List;

public record Recipe(
        String name,
        int calories,
        List<Ingredient> ingredients
) {
}