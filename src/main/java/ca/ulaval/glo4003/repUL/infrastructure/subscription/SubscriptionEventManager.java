package ca.ulaval.glo4003.repUL.infrastructure.subscription;

import ca.ulaval.glo4003.repUL.application.subscription.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionEventManager implements SubscriptionEventNotifier, SubscriptionEventSubscriber {
    private final Map<SubscriptionEventType, List<SubscriptionEventListener>> eventMap;

    public SubscriptionEventManager() {
         this.eventMap = new HashMap<>();
    }

    @Override
    public void notify(
            SubscriptionEventType subscriptionEventType,
            SubscriptionEvent subscriptionEvent
    ) {
        List<SubscriptionEventListener> subscriptionEventListeners = eventMap.get(subscriptionEventType);

        if (subscriptionEventListeners == null) {
            return;
        }
        for (SubscriptionEventListener subscriptionEventListener: subscriptionEventListeners) {
            subscriptionEventListener.receiveEvent(subscriptionEvent);
        }
    }

    @Override
    public void subscribe(
            SubscriptionEventType subscriptionEventType,
            SubscriptionEventListener subscriptionEventListener
    ) {
        List<SubscriptionEventListener> subscriptionEventListeners = eventMap.get(subscriptionEventType);

        if (subscriptionEventListeners == null) {
            subscriptionEventListeners = new ArrayList<>();
        }
        subscriptionEventListeners.add(subscriptionEventListener);
        eventMap.put(subscriptionEventType, subscriptionEventListeners);
    }
}
