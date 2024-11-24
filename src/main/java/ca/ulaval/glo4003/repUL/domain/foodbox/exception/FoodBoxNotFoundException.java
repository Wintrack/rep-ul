package ca.ulaval.glo4003.repUL.domain.foodbox.exception;

import jakarta.ws.rs.NotFoundException;

public class FoodBoxNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Food box not found";

    public FoodBoxNotFoundException() {
        super(MESSAGE);
    }
}
