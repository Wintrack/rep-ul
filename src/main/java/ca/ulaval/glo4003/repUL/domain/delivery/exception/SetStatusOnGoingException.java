package ca.ulaval.glo4003.repUL.domain.delivery.exception;

import jakarta.ws.rs.BadRequestException;

public class SetStatusOnGoingException extends BadRequestException {
    private static final String MESSAGE = "Package delivered impossible to set on going";

    public SetStatusOnGoingException() {
        super(MESSAGE);
    }
}
