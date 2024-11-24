package ca.ulaval.glo4003.repUL.application.auth.exception;

import jakarta.ws.rs.NotAuthorizedException;

public class UserNotAuthorizedException extends NotAuthorizedException {
    private static final String MESSAGE = "User not authorized";

    public UserNotAuthorizedException() {
        super(MESSAGE);
    }
}
