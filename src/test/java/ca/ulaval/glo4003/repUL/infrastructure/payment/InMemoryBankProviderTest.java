package ca.ulaval.glo4003.repUL.infrastructure.payment;

import ca.ulaval.glo4003.repUL.application.payment.infra.PaymentSystem;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryBankProviderTest {
    private PaymentSystem paymentSystem;

    @BeforeEach
    void setup() {
        paymentSystem = new InMemoryPaymentSystem();
    }

    @Test
    void givenCreditCardAndAmount_whenCreditMoney_thenAlwaysReturnTrue() {
        // GIVEN
        CreditCard creditCard = new CreditCard(
                "5415 9052 3492 1230",
                0,
                LocalDate.now().plusDays(42),
                "Bob Thetestor"
        );
        int amount = 75;

        // WHEN
        boolean transactionResult = paymentSystem.creditMoney(creditCard, amount);

        // THEN
        assertThat(transactionResult).isTrue();

    }

}