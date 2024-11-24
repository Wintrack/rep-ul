package ca.ulaval.glo4003.repUL.application.foodbox;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.infra.FoodBoxRepository;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;
import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FoodBoxServiceTest {
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

    private final FoodBoxRepository foodBoxRepository;

    private final FoodBoxService foodBoxService;

    public FoodBoxServiceTest() {
        foodBoxRepository = Mockito.mock();
        ServiceLocator.getInstance().registerService(FoodBoxRepository.class, foodBoxRepository);
        foodBoxService = new FoodBoxService();
    }

    @Test
    void whenGetFoodBoxes_thenCallGetFoodBoxesInRepository() {
        // GIVEN
        List<FoodBox> foodBoxes = List.of(new FoodBox(ID, STANDARD, RECIPES));

        when(foodBoxRepository.getFoodBoxes()).thenReturn(foodBoxes);

        // WHEN
        List<FoodBox> foodBoxesFound = foodBoxService.getFoodBoxes();

        // THEN
        assertThat(foodBoxesFound).isEqualTo(foodBoxes);
    }

    @Test
    void givenId_whenGetFoodBoxById_thenCallGetFoodBoxByIdInRepository() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);

        when(foodBoxRepository.getFoodBoxById(ID)).thenReturn(foodBox);

        // WHEN
        FoodBox foodBoxFound = foodBoxRepository.getFoodBoxById(ID);

        // THEN
        assertThat(foodBox).isEqualTo(foodBoxFound);
    }

    @Test
    void givenIdAndRecipeName_whenGetIngredients_thenCallGetIngredientsInRepository() {
        // GIVEN
        FoodBox foodBox = new FoodBox(ID, STANDARD, RECIPES);
        List<Ingredient> ingredients = foodBox.getRecipes().get(0).ingredients();

        when(foodBoxRepository.getIngredients(ID, "Boeuf Bourgignon"))
                .thenReturn(ingredients);

        // WHEN
        List<Ingredient> ingredientsFound = foodBoxRepository.getIngredients(ID, "Boeuf Bourgignon");

        // THEN
        assertThat(ingredientsFound).isEqualTo(ingredients);
    }
}