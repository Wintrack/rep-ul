package ca.ulaval.glo4003.repUL.api.auth.exception;

import jakarta.ws.rs.BadRequestException;

public class AuthEntityAlreadyExists extends BadRequestException {
    private static final String MESSAGE = "AuthEntity already exists";

    public AuthEntityAlreadyExists() {
        super(MESSAGE);
    }
}
