package ca.ulaval.glo4003.repUL.infrastructure.foodbox;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;
import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception.FoodBoxNotFoundException;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception.RecipeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class FoodBoxRepositoryImplTest {
    private FoodBoxRepositoryImpl foodBoxRepository;

    private static final String ID = UUID.randomUUID().toString();

    private static final List<Recipe> RECIPES = List.of(
            new Recipe(
                    "Boeuf Bourgignon",
                    42,
                    List.of(
                            new Ingredient("Wine", "1 bottle"),
                            new Ingredient("Beef", "1 kg")
                    )
            )
    );

    @BeforeEach
    void setUp() {
        foodBoxRepository = new FoodBoxRepositoryImpl();
    }

    @Test
    void givenCreatedFoodBox_whenGetFoodBoxes_thenReturnAllFoodBoxes() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        foodBoxRepository.createFoodBox(foodBox);

        // WHEN
        List<FoodBox> foodBoxes = foodBoxRepository.getFoodBoxes();

        // THEN
        assertThat(foodBoxes).isEqualTo(List.of(foodBox));
    }

    @Test
    void givenCreatedFoodBox_whenGetFoodBoxById_thenReturnFoodBoxFound() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        foodBoxRepository.createFoodBox(foodBox);

        // WHEN
        FoodBox foodBoxFound = foodBoxRepository.getFoodBoxById(foodBox.getId());

        // THEN
        assertThat(foodBoxFound).isEqualTo(foodBox);
    }

    @Test
    void givenWrongId_whenGetFoodBoxById_thenThrowFoodBoxNotFoundException() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        foodBoxRepository.createFoodBox(foodBox);

        // WHEN
        assertThatThrownBy(
                () -> {
                    foodBoxRepository.getFoodBoxById("wrong id");
                })
                .isInstanceOf(FoodBoxNotFoundException.class)
                .hasMessageContaining("FoodBox not found");
    }

    @Test
    void givenCreatedFoodBox_whenGetIngredients_thenGetIngredients() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        foodBoxRepository.createFoodBox(foodBox);

        // WHEN
        List<Ingredient> ingredients = foodBoxRepository
                .getIngredients(foodBox.getId(), "Boeuf Bourgignon");


        // THEN
        List<Ingredient> expectedIngredients = foodBox.getRecipes().get(0).ingredients();

        assertThat(ingredients).isEqualTo(expectedIngredients);
    }

    @Test
    void givenWrongRecipeName_whenGetIngredients_thenThrowRecipeNotFoundException() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        foodBoxRepository.createFoodBox(foodBox);

        // WHEN
        assertThatThrownBy(
                () -> {
                    foodBoxRepository.getIngredients(foodBox.getId(), "Pizza");
                })
                .isInstanceOf(RecipeNotFoundException.class)
                .hasMessageContaining("Recipe not found");
    }
}