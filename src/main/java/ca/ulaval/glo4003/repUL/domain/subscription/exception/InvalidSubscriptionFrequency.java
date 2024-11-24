package ca.ulaval.glo4003.repUL.domain.subscription.exception;

import jakarta.ws.rs.BadRequestException;

public class InvalidSubscriptionFrequency extends BadRequestException {
    private static final String MESSAGE = "Subscription frequency invalid";

    public InvalidSubscriptionFrequency() {
        super(MESSAGE);
    }
}
