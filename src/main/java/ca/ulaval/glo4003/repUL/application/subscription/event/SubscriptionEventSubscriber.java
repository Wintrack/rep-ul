package ca.ulaval.glo4003.repUL.application.subscription.event;

public interface SubscriptionEventSubscriber {
    void subscribe(
            SubscriptionEventType subscriptionEventType,
            SubscriptionEventListener subscriptionEventListener
    );
}
