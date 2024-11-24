package ca.ulaval.glo4003.repUL.domain.subscription.exception;

import jakarta.ws.rs.BadRequestException;

public class SubscriptionRefusedImpossibleException extends BadRequestException {
    private static final String MESSAGE = "Impossible to refuse subscription " +
            "subscription already accepted";

    public SubscriptionRefusedImpossibleException() {
        super(MESSAGE);
    }
}
