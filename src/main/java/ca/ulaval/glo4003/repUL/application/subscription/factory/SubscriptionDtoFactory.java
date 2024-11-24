package ca.ulaval.glo4003.repUL.application.subscription.factory;

import ca.ulaval.glo4003.repUL.application.subscription.dto.SubscriptionFoundDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.SubscriptionFrequencyFound;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;

public class SubscriptionDtoFactory {
    public SubscriptionFoundDto toSubscriptionFound(
            Subscription subscription
    ) {
        String localDateTimeString = null;

        if (subscription.getFrequency().getDate() != null) {
            localDateTimeString = subscription.getFrequency().getDate().toString();
        }

        return new SubscriptionFoundDto(
                subscription.getId(),
                new SubscriptionFrequencyFound(
                        subscription.getFrequency().getFrequencyType(),
                        localDateTimeString
                ),
                subscription.getLocation(),
                subscription.getFoodBox(),
                subscription.getStartDate().toString(),
                subscription.getSubscriptionFoodBoxStatus()
        );
    }
}
