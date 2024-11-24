package ca.ulaval.glo4003.repUL.application.foodbox;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.infra.FoodBoxRepository;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.foodbox.ingredient.Ingredient;

import java.util.List;

public class FoodBoxService {
    private final FoodBoxRepository foodBoxRepository;

    public FoodBoxService() {
        this.foodBoxRepository = ServiceLocator.getInstance().getService(FoodBoxRepository.class);
    }

    public List<FoodBox> getFoodBoxes() {
        return foodBoxRepository.getFoodBoxes();
    }

    public FoodBox getFoodBoxById(String id) {
        return foodBoxRepository.getFoodBoxById(id);
    }

    public List<Ingredient> getIngredients(String id, String recipeName) {
        return foodBoxRepository.getIngredients(id, recipeName);
    }
}
