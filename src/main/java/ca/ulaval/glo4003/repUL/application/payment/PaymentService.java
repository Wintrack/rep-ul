package ca.ulaval.glo4003.repUL.application.payment;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.payment.infra.PaymentSystem;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import ca.ulaval.glo4003.repUL.domain.payment.exception.CardTransactionFailed;

public class PaymentService {

    private final PaymentSystem paymentSystem;

    public PaymentService() {
        this.paymentSystem = ServiceLocator.getInstance().getService(PaymentSystem.class);
    }

    public void creditCard(CreditCard creditCard, int amount) {
        if (!paymentSystem.creditMoney(creditCard, amount)) {
            throw new CardTransactionFailed();
        }
    }
}
