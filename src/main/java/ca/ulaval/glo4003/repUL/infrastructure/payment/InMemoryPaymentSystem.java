package ca.ulaval.glo4003.repUL.infrastructure.payment;

import ca.ulaval.glo4003.repUL.application.payment.infra.PaymentSystem;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;


public class InMemoryPaymentSystem implements PaymentSystem {
    @Override
    public boolean creditMoney(CreditCard creditCard, int montant) {
        return true;
    }
}
