package ca.ulaval.glo4003.repUL.infrastructure.delivery.exception;

import jakarta.ws.rs.NotFoundException;

public class DeliveryNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Delivery not found exception";

    public DeliveryNotFoundException() {
        super(MESSAGE);
    }
}
