package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import ca.ulaval.glo4003.repUL.domain.subscription.exception.FrequencyDateToLate;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubscriptionFrequencyTest {

    @Test
    void givenExpiredSubscriptionFrequency_whenRefuseDate_thenThrowFrequencyDateToLate() {
        // GIVEN
        SubscriptionFrequency subscriptionFrequency = mock(Answers.CALLS_REAL_METHODS);


        when(subscriptionFrequency.isExpired()).thenReturn(true);

        // THEN
        assertThatThrownBy(subscriptionFrequency::refuseDate)
                .isInstanceOf(FrequencyDateToLate.class)
                .hasMessageContaining("Frequency date expired");
    }

}