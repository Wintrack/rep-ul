package ca.ulaval.glo4003.repUL.application.subscription.factory;

import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEvent;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;

public class SubscriptionEventFactory {

    public SubscriptionEvent toSubscriptionEvent(Subscription subscription) {
        return new SubscriptionEvent(
                subscription.getId(),
                subscription.getUserEmail(),
                subscription.getLocation(),
                subscription.getFoodBox(),
                subscription.getFrequency()
        );
    }
}
