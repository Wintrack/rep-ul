package ca.ulaval.glo4003.repUL.api.subscription.assembler;

import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionSporadicallyRequest;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionWeeklyRequest;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSporadicSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionCardDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;

public class SubscriptionAssembler {
    public CreateSubscriptionDto toSubscription(
            String email,
            SubscriptionWeeklyRequest subscriptionWeeklyRequest
    ) {
        CreateSubscriptionCardDto creditCard = new CreateSubscriptionCardDto(
                subscriptionWeeklyRequest.creditCard().CardNumber(),
                subscriptionWeeklyRequest.creditCard().ccv(),
                subscriptionWeeklyRequest.creditCard().expDate(),
                subscriptionWeeklyRequest.creditCard().fullName()
        );

        return new CreateSubscriptionDto(
                email,
                creditCard,
                subscriptionWeeklyRequest.foodBoxId(),
                subscriptionWeeklyRequest.frequency(),
                subscriptionWeeklyRequest.location()
        );
    }

    public CreateSporadicSubscriptionDto toSubscription(
            String email,
            SubscriptionSporadicallyRequest subscriptionSporadicallyRequest
    ) {
        CreateSubscriptionCardDto creditCard = new CreateSubscriptionCardDto(
                subscriptionSporadicallyRequest.creditCard().CardNumber(),
                subscriptionSporadicallyRequest.creditCard().ccv(),
                subscriptionSporadicallyRequest.creditCard().expDate(),
                subscriptionSporadicallyRequest.creditCard().fullName()
        );

        return new CreateSporadicSubscriptionDto(
                email,
                creditCard,
                subscriptionSporadicallyRequest.foodBoxId(),
                subscriptionSporadicallyRequest.location()
        );
    }
}
