package ca.ulaval.glo4003.repUL.api.foodbox.assembler.recipe;

import ca.ulaval.glo4003.repUL.api.foodbox.assembler.ingredient.IngredientDtoAssembler;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe.CreateRecipeRequest;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe.RecipeFoundResponse;
import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;

public class RecipeDtoAssembler {
    private final IngredientDtoAssembler ingredientDtoAssembler;

    public RecipeDtoAssembler() {
        ingredientDtoAssembler = new IngredientDtoAssembler();
    }

    public Recipe toRecipe(CreateRecipeRequest createRecipeRequest) {
        return new Recipe(
                createRecipeRequest.name(),
                createRecipeRequest.calories(),
                createRecipeRequest.ingredients()
                        .stream().map(ingredientDtoAssembler::toIngredient)
                        .toList()
        );
    }

    public RecipeFoundResponse toRecipeFoundResponse(Recipe recipe) {
        return new RecipeFoundResponse(
                recipe.name(),
                recipe.calories(),
                recipe.ingredients()
                        .stream().map(ingredientDtoAssembler::toIngredientFoundResponse)
                        .toList()
        );
    }
}
