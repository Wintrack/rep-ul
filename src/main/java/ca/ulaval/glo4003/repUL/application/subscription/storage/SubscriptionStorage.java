package ca.ulaval.glo4003.repUL.application.subscription.storage;

import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;

import java.util.List;

public interface SubscriptionStorage {

    void createSubscription(Subscription subscription);

    Subscription getSubscriptionByUserEmailAndId(String userEmail, String id);

    List<Subscription> getSubscriptionsByUserEmail(String userEmail);

    List<Subscription> getSubscriptions();

    void updateSubscription(Subscription subscription);

    void updateSubscriptions(List<Subscription> subscriptions);
}
