package ca.ulaval.glo4003.repUL.domain.delivery.exception;

import jakarta.ws.rs.NotFoundException;

public class ThrowErrorDelivery extends NotFoundException {
    private static final String MESSAGE = "Delivery not found";

    public ThrowErrorDelivery() {
        super(MESSAGE);
    }
}

