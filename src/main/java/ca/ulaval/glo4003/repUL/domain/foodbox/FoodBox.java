package ca.ulaval.glo4003.repUL.domain.foodbox;

import ca.ulaval.glo4003.repUL.domain.foodbox.recipe.Recipe;
import ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType;

import java.util.List;
import java.util.Objects;

public class FoodBox {
    private String id;

    private final BoxType type;

    private final List<Recipe> recipes;

    public FoodBox(String id, BoxType type, List<Recipe> recipes) {
        this.id = id;
        this.type = type;
        this.recipes = recipes;
    }

    public FoodBox(String id) {
        this.id = id;
        type = null;
        recipes = null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BoxType getType() {
        return type;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoodBox foodBox = (FoodBox) o;
        return Objects.equals(id, foodBox.id) &&
                type == foodBox.type &&
                Objects.equals(recipes, foodBox.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, recipes);
    }
}