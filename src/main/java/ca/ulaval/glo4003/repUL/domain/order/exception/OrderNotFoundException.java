package ca.ulaval.glo4003.repUL.domain.order.exception;

import jakarta.ws.rs.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Order not found";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}
