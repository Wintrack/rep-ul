package ca.ulaval.glo4003.repUL.domain.order.exception;

import jakarta.ws.rs.BadRequestException;

public class NotAssembledOrReadyOrderException extends BadRequestException {
    private static final String MESSAGE = "Order must be assembled or ready";

    public NotAssembledOrReadyOrderException() {
        super(MESSAGE);
    }
}
