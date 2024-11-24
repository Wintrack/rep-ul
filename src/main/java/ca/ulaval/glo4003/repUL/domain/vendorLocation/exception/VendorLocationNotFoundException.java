package ca.ulaval.glo4003.repUL.domain.vendorLocation.exception;

import jakarta.ws.rs.NotFoundException;

public class VendorLocationNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Vendor location not found";

    public VendorLocationNotFoundException() {
        super(MESSAGE);
    }
}
