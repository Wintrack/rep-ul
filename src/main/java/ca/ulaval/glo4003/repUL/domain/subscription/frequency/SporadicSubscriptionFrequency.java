package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType.SPORADIC;
import static java.time.LocalDateTime.now;

public class SporadicSubscriptionFrequency extends SubscriptionFrequency {
    public SporadicSubscriptionFrequency() {
        super(SPORADIC, null);
    }

    @Override
    public void acceptDate() {
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

        if (dayOfWeek == DayOfWeek.THURSDAY) {
            date = LocalDateTime.now().plusDays(4);
            return;
        }
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            date = LocalDateTime.now().plusDays(3);
            return;
        }
        date = LocalDateTime.now().plusDays(2);
    }

    @Override
    public boolean isExpired() {
        if (date == null) {
            return false;
        }
        return date.minusDays(2).isBefore(now());
    }
}
