package ca.ulaval.glo4003.repUL.domain.subscription.exception;

import jakarta.ws.rs.BadRequestException;

public class FrequencyDateToLate extends BadRequestException {
    private static final String MESSAGE = "Frequency date expired";

    public FrequencyDateToLate() {
        super(MESSAGE);
    }
}
