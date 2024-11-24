package ca.ulaval.glo4003.repUL.domain.payment.exception;

import jakarta.ws.rs.NotAuthorizedException;

public class CardTransactionFailed extends NotAuthorizedException {
    private static final String MESSAGE = "Card transaction failed";

    public CardTransactionFailed() {
        super(MESSAGE);
    }
}
