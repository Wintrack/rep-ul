package ca.ulaval.glo4003.repUL.domain.delivery.exception;

import jakarta.ws.rs.BadRequestException;

public class AbortPackageDeliveryException extends BadRequestException {
    private static final String MESSAGE = "Impossible to abort package, package not in Delivery";

    public AbortPackageDeliveryException() {
        super(MESSAGE);
    }
}
