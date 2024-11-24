package ca.ulaval.glo4003.repUL.domain.subscription.exception;

import jakarta.ws.rs.NotFoundException;

public class SubscriptionNotFound extends NotFoundException {
    private static final String MESSAGE = "Subscription not found";

    public SubscriptionNotFound() {
        super(MESSAGE);
    }
}
