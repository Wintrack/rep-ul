package ca.ulaval.glo4003.repUL.domain.subscription;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import ca.ulaval.glo4003.repUL.domain.subscription.exception.SubscriptionRefusedImpossibleException;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;
import ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionStatus;

import java.time.LocalDate;
import java.util.Objects;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionStatus.*;
import static java.time.LocalDateTime.now;

public class Subscription {
    private String id;
    private final String userEmail;
    private final CreditCard creditCard;
    private final SubscriptionFrequency frequency;
    private String location;
    private final LocalDate startDate;
    private FoodBox foodBox;
    private SubscriptionStatus subscriptionFoodBoxStatus;

    public Subscription(
            String id,
            String userEmail,
            CreditCard creditCard,
            SubscriptionFrequency frequency,
            String location,
            FoodBox foodBox,
            LocalDate startDate
    ) {
        this.id = id;
        this.userEmail = userEmail;
        this.creditCard = creditCard;
        this.frequency = frequency;
        this.location = location;
        this.foodBox = foodBox;
        this.startDate = startDate;
        this.subscriptionFoodBoxStatus = NOT_CONFIRMED;
    }

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public SubscriptionFrequency getFrequency() {
        return frequency;
    }

    public String getLocation() {
        return location;
    }

    public FoodBox getFoodBox() {
        return foodBox;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public SubscriptionStatus getSubscriptionFoodBoxStatus() {
        return subscriptionFoodBoxStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFoodBox(FoodBox foodBox) {
        this.foodBox = foodBox;
    }

    public void setFoodBoxAccepted() {
        frequency.acceptDate();
        this.subscriptionFoodBoxStatus = ACCEPTED;
    }

    public void setFoodBoxRefused() {
        frequency.refuseDate();
        if (subscriptionFoodBoxStatus == ACCEPTED) {
            throw new SubscriptionRefusedImpossibleException();
        }
        this.subscriptionFoodBoxStatus = REFUSED;
    }

    public void invalidNotConfirmed() {
        if (subscriptionFoodBoxStatus != NOT_CONFIRMED) {
            return;
        }
        if (frequency.isExpired()) {
            subscriptionFoodBoxStatus = REFUSED;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(creditCard, that.creditCard) &&
                frequency == that.frequency &&
                Objects.equals(location, that.location) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(foodBox, that.foodBox) &&
                subscriptionFoodBoxStatus == that.subscriptionFoodBoxStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                userEmail,
                creditCard,
                frequency,
                location,
                startDate,
                foodBox,
                subscriptionFoodBoxStatus
        );
    }
}
