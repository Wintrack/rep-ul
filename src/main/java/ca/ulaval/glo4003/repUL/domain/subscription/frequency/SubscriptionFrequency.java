package ca.ulaval.glo4003.repUL.domain.subscription.frequency;

import ca.ulaval.glo4003.repUL.domain.subscription.exception.FrequencyDateToLate;
import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public abstract class SubscriptionFrequency {
    protected final SubscriptionFrequencyType frequencyType;

    protected LocalDateTime date;

    public SubscriptionFrequency(
            SubscriptionFrequencyType frequencyType,
            LocalDateTime date
    ) {
        this.frequencyType = frequencyType;
        this.date = date;
    }

    public SubscriptionFrequencyType getFrequencyType() {
        return frequencyType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void refuseDate() {
        if (isExpired()) {
            throw new FrequencyDateToLate();
        }
    }

    public abstract void acceptDate();

    public abstract boolean isExpired();
}
