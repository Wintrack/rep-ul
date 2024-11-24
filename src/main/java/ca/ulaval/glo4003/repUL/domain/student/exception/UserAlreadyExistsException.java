package ca.ulaval.glo4003.repUL.domain.student.exception;

import jakarta.ws.rs.BadRequestException;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super("User with provided email or IDUL already exist. Please login instead");
    }
}
