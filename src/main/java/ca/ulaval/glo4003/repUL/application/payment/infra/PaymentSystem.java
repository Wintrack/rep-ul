package ca.ulaval.glo4003.repUL.application.payment.infra;

import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;

public interface PaymentSystem {
    boolean creditMoney(CreditCard creditCard, int montant);
}
