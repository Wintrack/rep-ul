package ca.ulaval.glo4003.repUL.application.order.exception;

import jakarta.ws.rs.BadRequestException;

public class OrderPreparerNotFoundException extends BadRequestException {
    private static final String MESSAGE = "Order preparer not found";

    public OrderPreparerNotFoundException() {
        super(MESSAGE);
    }
}
