package ca.ulaval.glo4003.repUL.application.subscription.exception;

import jakarta.ws.rs.BadRequestException;

public class SubscriptionWrongDayException extends BadRequestException {
    private static final String MESSAGE = "Frequency day must be a day of the week";

    public SubscriptionWrongDayException() {
        super(MESSAGE);
    }
}
