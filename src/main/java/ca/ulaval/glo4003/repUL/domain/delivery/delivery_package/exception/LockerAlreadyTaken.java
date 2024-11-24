package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception;

import jakarta.ws.rs.BadRequestException;

public class LockerAlreadyTaken extends BadRequestException {
    private static final String MESSAGE = "Locker already taken";

    public LockerAlreadyTaken() {
        super(MESSAGE);
    }
}
