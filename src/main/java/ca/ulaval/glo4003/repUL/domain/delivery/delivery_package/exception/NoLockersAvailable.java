package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception;

import jakarta.ws.rs.BadRequestException;

public class NoLockersAvailable extends BadRequestException {
    private static final String MESSAGE = "No locker available";

    public NoLockersAvailable() {
        super(MESSAGE);
    }
}
