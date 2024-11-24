package ca.ulaval.glo4003.repUL.domain.order.exception;

import jakarta.ws.rs.BadRequestException;

public class NotSelectedOrderException extends BadRequestException {
    private static final String MESSAGE = "Order not selected";

    public NotSelectedOrderException() {
        super(MESSAGE);
    }
}
