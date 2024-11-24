package ca.ulaval.glo4003.repUL.api.foodbox.assembler;

import ca.ulaval.glo4003.repUL.api.foodbox.assembler.recipe.RecipeDtoAssembler;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.CreateFoodBoxRequest;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.FoodBoxFoundResponse;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;

public class FoodBoxDtoAssembler {
    private final RecipeDtoAssembler recipeDtoAssembler;

    public FoodBoxDtoAssembler() {
        recipeDtoAssembler = new RecipeDtoAssembler();
    }

    public FoodBox toFoodBox(CreateFoodBoxRequest createFoodBoxRequest) {
        return new FoodBox(
                null,
                createFoodBoxRequest.type(),
                createFoodBoxRequest.recipes()
                        .stream().map(recipeDtoAssembler::toRecipe)
                        .toList()
        );
    }

    public FoodBoxFoundResponse toFoodBoxFound(FoodBox foodBox) {
        return new FoodBoxFoundResponse(
                foodBox.getId(),
                foodBox.getType(),
                foodBox.getRecipes()
                        .stream().map(recipeDtoAssembler::toRecipeFoundResponse)
                        .toList()
        );
    }
}
