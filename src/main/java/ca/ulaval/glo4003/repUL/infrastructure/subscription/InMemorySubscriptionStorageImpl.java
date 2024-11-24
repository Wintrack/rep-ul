package ca.ulaval.glo4003.repUL.infrastructure.subscription;

import ca.ulaval.glo4003.repUL.application.subscription.storage.SubscriptionStorage;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import ca.ulaval.glo4003.repUL.infrastructure.subscription.exception.SubscriptionNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.UUID.randomUUID;

public class InMemorySubscriptionStorageImpl implements SubscriptionStorage {
    private final Map<String, Subscription> subscriptionMap;

    public InMemorySubscriptionStorageImpl() {
        subscriptionMap = new HashMap<>();
    }

    @Override
    public void createSubscription(Subscription subscription) {
        subscription.setId(randomUUID().toString());
        subscriptionMap.put(subscription.getId(), subscription);
    }


    @Override
    public List<Subscription> getSubscriptionsByUserEmail(String userEmail) {
        return subscriptionMap.values()
                .stream().filter((subscription -> {
                    return subscription.getUserEmail().equals(userEmail);
                })).toList();
    }

    @Override
    public List<Subscription> getSubscriptions() {
        return subscriptionMap.values().stream().toList();
    }

    @Override
    public Subscription getSubscriptionByUserEmailAndId(String userEmail, String id) {
        Subscription subscriptionFound = subscriptionMap.get(id);

        if (subscriptionFound == null) {
            throw new SubscriptionNotFound();
        }
        if (!subscriptionFound.getUserEmail().equals(userEmail)) {
            throw new SubscriptionNotFound();
        }
        return subscriptionFound;
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        Subscription subscriptionFound = subscriptionMap.get(subscription.getId());

        if (subscriptionFound == null) {
            throw new SubscriptionNotFound();
        }
        subscriptionMap.put(subscription.getId(), subscription);
    }

    @Override
    public void updateSubscriptions(List<Subscription> subscriptions) {
        subscriptions.forEach(this::updateSubscription);
    }
}
