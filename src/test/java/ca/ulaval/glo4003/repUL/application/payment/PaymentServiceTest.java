package ca.ulaval.glo4003.repUL.application.payment;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.payment.infra.PaymentSystem;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import ca.ulaval.glo4003.repUL.domain.payment.exception.CardTransactionFailed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    private PaymentSystem paymentSystem;

    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentSystem = mock();
        ServiceLocator.getInstance().registerService(PaymentSystem.class, paymentSystem);
        paymentService = new PaymentService();
    }

    @Test
    void givenCreditCardAndAmount_whenCreditCard_thenCreditMoneyToThePaymentSystem() {
        // GIVEN
        CreditCard creditCard = new CreditCard(
                "5415 9052 3492 1230",
                0,
                LocalDate.now().plusDays(42),
                "Bob Thetestor"
        );
        int amount = 75;

        when(paymentSystem.creditMoney(creditCard, amount)).thenReturn(true);
        // WHEN
        paymentService.creditCard(creditCard, amount);

        // THEN
        verify(paymentSystem).creditMoney(creditCard, amount);
    }

    @Test
    void givenCreditCardAndAmountWithWrongCard_whenCreditCard_thenThrowCardTransactionFailed() {
        // GIVEN
        CreditCard creditCard = new CreditCard(
                "5415 9052 3492 1230",
                0,
                LocalDate.now().plusDays(42),
                "Bob Thetestor"
        );
        int amount = 75;

        when(paymentSystem.creditMoney(creditCard, amount)).thenReturn(false);
        // THEN
        assertThatThrownBy(() -> {
            paymentService.creditCard(creditCard, amount);
        })
                .isInstanceOf(CardTransactionFailed.class);
    }
}