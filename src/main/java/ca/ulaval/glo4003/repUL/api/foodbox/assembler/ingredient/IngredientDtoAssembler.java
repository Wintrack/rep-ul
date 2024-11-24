package ca.ulaval.glo4003.repUL.api.foodbox.assembler.ingredient;

import ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient.CreateIngredientRequest;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient.IngredientFoundResponse;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;

public class IngredientDtoAssembler {

    public Ingredient toIngredient(CreateIngredientRequest createIngredientRequest) {
        return new Ingredient(
                createIngredientRequest.ingredient(),
                createIngredientRequest.quantity()
        );
    }

    public IngredientFoundResponse toIngredientFoundResponse(Ingredient ingredient) {
        return new IngredientFoundResponse(
                ingredient.ingredient(),
                ingredient.quantity()
        );
    }

}
