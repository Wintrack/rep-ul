package ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception;

import jakarta.ws.rs.NotFoundException;

public class RecipeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Recipe not found";

    public RecipeNotFoundException() {
        super(MESSAGE);
    }
}
