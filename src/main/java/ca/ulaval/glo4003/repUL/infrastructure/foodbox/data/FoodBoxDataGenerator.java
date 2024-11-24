package ca.ulaval.glo4003.repUL.infrastructure.foodbox.data;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.infra.FoodBoxRepository;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;
import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;

import java.util.List;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static java.util.UUID.randomUUID;

public class FoodBoxDataGenerator {
    private static final List<FoodBox> DATA = List.of(
            new FoodBox(
                    randomUUID().toString(),
                    STANDARD,
                    List.of(
                            new Recipe(
                                    "Spaghetti Carbonara",
                                    650,
                                    List.of(
                                            new Ingredient("spaghetti", "200g"),
                                            new Ingredient("egg", "2"),
                                            new Ingredient("pecorino cheese", "50g"),
                                            new Ingredient("guanciale", "100g")
                                    )
                            ),
                            new Recipe(
                                    "Chicken Salad",
                                    350,
                                    List.of(
                                            new Ingredient("chicken breast", "200g"),
                                            new Ingredient("lettuce", "1 head"),
                                            new Ingredient("tomato", "2"),
                                            new Ingredient("cucumber", "1")
                                    )
                            ),
                            new Recipe(
                                    "Pancakes",
                                    520,
                                    List.of(
                                            new Ingredient("flour", "200g"),
                                            new Ingredient("milk", "300ml"),
                                            new Ingredient("egg", "1"),
                                            new Ingredient("baking powder", "1 tsp")
                                    )
                            ),
                            new Recipe(
                                    "Guacamole",
                                    400,
                                    List.of(
                                            new Ingredient("avocado", "2"),
                                            new Ingredient("lime", "1"),
                                            new Ingredient("tomato", "1"),
                                            new Ingredient("onion", "0.5")
                                    )
                            ),
                            new Recipe(
                                    "Chocolate Cake",
                                    700,
                                    List.of(
                                            new Ingredient("flour", "200g"),
                                            new Ingredient("cocoa powder", "50g"),
                                            new Ingredient("sugar", "150g"),
                                            new Ingredient("egg", "2")
                                    )
                            )
                    )
            )
    );

    public static void createMockData() {
        FoodBoxRepository repository = ServiceLocator.getInstance().getService(FoodBoxRepository.class);

        DATA.forEach(repository::createFoodBox);
    }
}
