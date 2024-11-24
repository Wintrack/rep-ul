package ca.ulaval.glo4003.repUL.application.auth.exception;

import jakarta.ws.rs.ForbiddenException;

public class InvalidTokenException extends ForbiddenException {
    private static final String MESSAGE = "INVALID_CREDENTIALS";

    public InvalidTokenException() {
        super(MESSAGE);
    }
}
