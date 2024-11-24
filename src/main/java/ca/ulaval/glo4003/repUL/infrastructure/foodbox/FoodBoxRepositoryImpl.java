package ca.ulaval.glo4003.repUL.infrastructure.foodbox;

import ca.ulaval.glo4003.repUL.application.foodbox.infra.FoodBoxRepository;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;
import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception.FoodBoxNotFoundException;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception.RecipeNotFoundException;

import java.util.HashMap;
import java.util.List;

import static java.util.UUID.randomUUID;

public class FoodBoxRepositoryImpl implements FoodBoxRepository {
    private final HashMap<String, FoodBox> foodBoxHashMap;

    public FoodBoxRepositoryImpl() {
        this.foodBoxHashMap = new HashMap<>();
    }

    @Override
    public void createFoodBox(FoodBox foodBox) {
        foodBox.setId(randomUUID().toString());
        foodBoxHashMap.put(foodBox.getId(), foodBox);
    }

    @Override
    public List<FoodBox> getFoodBoxes() {
        return foodBoxHashMap.values().stream().toList();
    }

    @Override
    public FoodBox getFoodBoxById(String id) {
        FoodBox foodBox = foodBoxHashMap.get(id);

        if (foodBox == null) {
            throw new FoodBoxNotFoundException();
        }
        return foodBox;
    }

    @Override
    public List<Ingredient> getIngredients(String id, String recipeName) {
        FoodBox foodBox = getFoodBoxById(id);
        List<Recipe> recipes = foodBox.getRecipes();

        for (Recipe recipe : recipes) {
            if (recipe.name().equals(recipeName)) {
                return recipe.ingredients();
            }
        }
        throw new RecipeNotFoundException();
    }
}
