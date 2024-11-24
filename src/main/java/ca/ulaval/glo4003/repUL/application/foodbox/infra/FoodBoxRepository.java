package ca.ulaval.glo4003.repUL.application.foodbox.infra;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;

import java.util.List;

public interface FoodBoxRepository {
    void createFoodBox(FoodBox foodBox);

    List<FoodBox> getFoodBoxes();

    FoodBox getFoodBoxById(String id);

    List<Ingredient> getIngredients(String id, String recipeName);
}
