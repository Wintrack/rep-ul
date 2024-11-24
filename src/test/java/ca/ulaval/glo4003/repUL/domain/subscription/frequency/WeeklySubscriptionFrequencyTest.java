package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import ca.ulaval.glo4003.repUL.domain.subscription.exception.FrequencyDateToLate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WeeklySubscriptionFrequencyTest {
    @Test
    void givenWeeklySubscriptionFrequencyExpired_thenValidDate_thenThrowFrequencyDateToLate() {
        // GIVEN
        WeeklySubscriptionFrequency weeklySubscriptionFrequency = new WeeklySubscriptionFrequency(
                LocalDateTime.now().plusDays(1)
        );

        // THEN
        assertThatThrownBy(weeklySubscriptionFrequency::refuseDate)
                .isInstanceOf(FrequencyDateToLate.class)
                .hasMessageContaining("Frequency date expired");

    }

    @Test
    void givenWeeklySubscriptionFrequencyNotExpired_thenIsExpired_thenReturnFalse() {
        // GIVEN
        WeeklySubscriptionFrequency weeklySubscriptionFrequency = new WeeklySubscriptionFrequency(
                LocalDateTime.now().plusDays(3)
        );

        // GIVEN
        boolean result = weeklySubscriptionFrequency.isExpired();

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void givenWeeklySubscriptionFrequencyExpired_thenIsExpired_thenReturnTrue() {
        // GIVEN
        WeeklySubscriptionFrequency weeklySubscriptionFrequency = new WeeklySubscriptionFrequency(
                LocalDateTime.now().plusDays(1)
        );

        // GIVEN
        boolean result = weeklySubscriptionFrequency.isExpired();

        // THEN
        assertThat(result).isTrue();
    }

}