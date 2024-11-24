package ca.ulaval.glo4003.repUL.domain.order.exception;

import jakarta.ws.rs.BadRequestException;

public class NotConfirmedOrderException extends BadRequestException {
    private static final String MESSAGE = "Order not confirmed";

    public NotConfirmedOrderException() {
        super(MESSAGE);
    }
}
