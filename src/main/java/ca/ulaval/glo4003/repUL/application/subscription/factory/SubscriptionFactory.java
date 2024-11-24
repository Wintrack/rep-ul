package ca.ulaval.glo4003.repUL.application.subscription.factory;

import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSporadicSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import ca.ulaval.glo4003.repUL.application.subscription.exception.SubscriptionWrongDayException;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SporadicSubscriptionFrequency;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.WeeklySubscriptionFrequency;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class SubscriptionFactory {
    public Subscription toSubscription(
            CreateSubscriptionDto createSubscriptionDto,
            FoodBox foodBox,
            VendorLocation vendorLocation
    ) {
        CreditCard creditCard = new CreditCard(
                createSubscriptionDto.creditCard().CardNumber(),
                createSubscriptionDto.creditCard().ccv(),
                createSubscriptionDto.creditCard().expDate(),
                createSubscriptionDto.creditCard().fullName()
        );
        DayOfWeek dayOfWeek = createSubscriptionDto.frequency().getDayOfWeek();

        if (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY) {
            throw new SubscriptionWrongDayException();
        }

        SubscriptionFrequency subscriptionFrequency = new WeeklySubscriptionFrequency(
                createSubscriptionDto.frequency()
        );

        return new Subscription(
                null,
                createSubscriptionDto.userEmail(),
                creditCard,
                subscriptionFrequency,
                vendorLocation.getLocation(),
                foodBox,
                LocalDate.now()
        );
    }

    public Subscription toSubscription(
            CreateSporadicSubscriptionDto createSporadicSubscriptionDto,
            FoodBox foodBox,
            VendorLocation vendorLocation
    ) {
        CreditCard creditCard = new CreditCard(
                createSporadicSubscriptionDto.creditCard().CardNumber(),
                createSporadicSubscriptionDto.creditCard().ccv(),
                createSporadicSubscriptionDto.creditCard().expDate(),
                createSporadicSubscriptionDto.creditCard().fullName()
        );
        SubscriptionFrequency subscriptionFrequency = new SporadicSubscriptionFrequency();

        return new Subscription(
                null,
                createSporadicSubscriptionDto.userEmail(),
                creditCard,
                subscriptionFrequency,
                vendorLocation.getLocation(),
                foodBox,
                LocalDate.now()
        );
    }
}
