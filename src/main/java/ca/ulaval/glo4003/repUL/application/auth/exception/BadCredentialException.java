package ca.ulaval.glo4003.repUL.application.auth.exception;

import jakarta.ws.rs.ForbiddenException;

public class BadCredentialException extends ForbiddenException {
    public BadCredentialException() {
        super("Invalid Credentials");
    }
}
