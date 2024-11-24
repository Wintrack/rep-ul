package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import ca.ulaval.glo4003.repUL.domain.subscription.exception.FrequencyDateToLate;
import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class WeeklySubscriptionFrequency extends SubscriptionFrequency {

    public WeeklySubscriptionFrequency(LocalDateTime date) {
        super(SubscriptionFrequencyType.WEEKLY, date);
    }

    @Override
    public void acceptDate() {
        if (isExpired()) {
            throw new FrequencyDateToLate();
        }
    }

    @Override
    public boolean isExpired() {
        return date.minusDays(2).isBefore(now());
    }
}
