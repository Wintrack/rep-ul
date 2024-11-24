package ca.ulaval.glo4003.repUL.infrastructure.foodbox.exception;

import jakarta.ws.rs.NotFoundException;

public class FoodBoxNotFoundException extends NotFoundException {
    private static final String MESSAGE = "FoodBox not found";

    public FoodBoxNotFoundException() {
        super(MESSAGE);
    }
}
