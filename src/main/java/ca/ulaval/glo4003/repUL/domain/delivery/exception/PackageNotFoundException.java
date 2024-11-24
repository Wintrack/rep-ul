package ca.ulaval.glo4003.repUL.domain.delivery.exception;

import jakarta.ws.rs.NotFoundException;

public class PackageNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Package not found";

    public PackageNotFoundException() {
        super(MESSAGE);
    }
}
