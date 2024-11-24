package ca.ulaval.glo4003.repUL.domain.subscription;

import ca.ulaval.glo4003.repUL.domain.subscription.exception.SubscriptionRefusedImpossibleException;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class SubscriptionTest {
    @Test
    void givenSubscription_whenSetFoodBoxAccepted_thenCallConfirmDate() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        // WHEN
        subscription.setFoodBoxAccepted();

        // THEN
        verify(subscriptionFrequency, times(1)).acceptDate();
    }

    @Test
    void givenSubscription_whenSetFoodBoxAccepted_thenSetStatusAccepted() {
        // GIVEN
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                mock(),
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        // WHEN
        subscription.setFoodBoxAccepted();

        // THEN
        assertThat(subscription.getSubscriptionFoodBoxStatus()).isEqualTo(ACCEPTED);
    }

    @Test
    void givenSubscription_whenSetFoodBoxRefused_thenCallRefuseDate() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        // WHEN
        subscription.setFoodBoxRefused();

        // THEN
        verify(subscriptionFrequency, times(1)).refuseDate();
    }

    @Test
    void givenSubscriptionWithStatusAccepted_whenSetFoodBoxRefused_thenThrowSubscriptionRefusedImpossibleException() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        subscription.setFoodBoxAccepted();
        // WHEN
        assertThatThrownBy(subscription::setFoodBoxRefused)
                .isInstanceOf(SubscriptionRefusedImpossibleException.class)
                .hasMessageContaining(
                        "Impossible to refuse subscription " +
                                "subscription already accepted"
                );
    }

    @Test
    void givenSubscription_whenSetFoodBoxRefused_thenSetStatusRefused() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        // WHEN
        subscription.setFoodBoxRefused();

        // THEN
        assertThat(subscription.getSubscriptionFoodBoxStatus()).isEqualTo(REFUSED);
    }

    @Test
    void givenSubscriptionAccepted_whenInvalidNotConfirmed_thenSubscriptionKeepItsStatus() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        subscription.setFoodBoxAccepted();
        // WHEN
        subscription.invalidNotConfirmed();

        // THEN
        assertThat(subscription.getSubscriptionFoodBoxStatus()).isEqualTo(ACCEPTED);
    }

    @Test
    void givenSubscriptionWithExpiredFrequency_whenInvalidNotConfirmed_thenSetStatusRefused() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        when(subscriptionFrequency.isExpired()).thenReturn(true);

        // WHEN
        subscription.invalidNotConfirmed();

        // THEN
        assertThat(subscription.getSubscriptionFoodBoxStatus()).isEqualTo(REFUSED);
    }

    @Test
    void givenSubscriptionWithANotExpiredFrequency_whenInvalidNotConfirmed_thenSubscriptionKeepItsStatus() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock();
        Subscription subscription = new Subscription(
                UUID.randomUUID().toString(),
                "test@test.com",
                mock(),
                subscriptionFrequency,
                "DESJARDINS",
                mock(),
                LocalDate.now()
        );

        when(subscriptionFrequency.isExpired()).thenReturn(false);

        // WHEN
        subscription.invalidNotConfirmed();

        // THEN
        assertThat(subscription.getSubscriptionFoodBoxStatus()).isEqualTo(NOT_CONFIRMED);
    }
}