package ca.ulaval.glo4003.repUL.api.config.authentication.exception;

import jakarta.ws.rs.ForbiddenException;

public class InvalidCredentialFilterException extends ForbiddenException {
    private static final String MESSAGE = "INVALID_CREDENTIALS";

    public InvalidCredentialFilterException() {
        super(MESSAGE);
    }
}
