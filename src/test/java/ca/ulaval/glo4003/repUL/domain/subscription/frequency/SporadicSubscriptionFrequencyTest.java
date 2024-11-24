package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SporadicSubscriptionFrequencyTest {
    @Test
    void givenSporadicSubscriptionFrequencyNotAFridayOrThursday_whenAcceptDate_thenSetDateInTwoDays() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();
        LocalDateTime today = LocalDateTime.of(2023, 12, 13, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);

        localDateTimeMocked
                .when(LocalDateTime::now)
                .thenReturn(today);

        // WHEN
        sporadicSubscriptionFrequency.acceptDate();

        // THEN
        LocalDateTime expectedResult = today.plusDays(2);

        assertThat(sporadicSubscriptionFrequency.date).isEqualTo(expectedResult);

        // FINALLY
        localDateTimeMocked.close();
    }

    @Test
    void givenSporadicSubscriptionFrequencyOnThursday_whenAcceptDate_thenSetDateInThreeDays() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();
        LocalDateTime today = LocalDateTime.of(2023, 12, 14, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);

        localDateTimeMocked
                .when(LocalDateTime::now)
                .thenReturn(today);

        // WHEN
        sporadicSubscriptionFrequency.acceptDate();

        // THEN
        LocalDateTime expectedResult = today.plusDays(4);

        assertThat(sporadicSubscriptionFrequency.date).isEqualTo(expectedResult);

        // FINALLY
        localDateTimeMocked.close();
    }

    @Test
    void givenSporadicSubscriptionFrequencyOnAFriday_whenAcceptDate_thenSetDateInThreeDays() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();
        LocalDateTime today = LocalDateTime.of(2023, 12, 15, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);

        localDateTimeMocked
                .when(LocalDateTime::now)
                .thenReturn(today);

        // WHEN
        sporadicSubscriptionFrequency.acceptDate();

        // THEN
        LocalDateTime expectedResult = today.plusDays(3);

        assertThat(sporadicSubscriptionFrequency.date).isEqualTo(expectedResult);

        // FINALLY
        localDateTimeMocked.close();
    }

    @Test
    void givenNotExpiredSubscriptionFrequency_whenIsExpired_thenReturnFalse() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();
        LocalDateTime today = LocalDateTime.of(2023, 12, 13, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);

        localDateTimeMocked.when(LocalDateTime::now).thenReturn(today);
        sporadicSubscriptionFrequency.acceptDate();

        // WHEN
        boolean expired = sporadicSubscriptionFrequency.isExpired();

        // THEN
        assertThat(expired).isEqualTo(false);

        // FINALLY
        localDateTimeMocked.close();
    }

    @Test
    void givenExpiredSubscriptionFrequency_whenIsExpired_thenReturnTrue() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();
        LocalDateTime today = LocalDateTime.of(2023, 12, 13, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);

        localDateTimeMocked.when(LocalDateTime::now).thenReturn(today);
        sporadicSubscriptionFrequency.acceptDate();

        localDateTimeMocked.when(LocalDateTime::now).thenReturn(today.plusDays(2));

        // WHEN
        boolean expired = sporadicSubscriptionFrequency.isExpired();

        // THEN
        assertThat(expired).isEqualTo(true);

        // FINALLY
        localDateTimeMocked.close();
    }

    @Test
    void givenExpiredSubscriptionNoAccepted_whenIsExpired_thenReturnFalse() {
        // GIVEN
        SporadicSubscriptionFrequency sporadicSubscriptionFrequency = new SporadicSubscriptionFrequency();

        // WHEN
        boolean expired = sporadicSubscriptionFrequency.isExpired();

        // THEN
        assertThat(expired).isEqualTo(false);
    }
}