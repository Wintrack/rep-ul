package ca.ulaval.glo4003.repUL.application.subscription.event;

public interface SubscriptionEventNotifier {
    void notify(SubscriptionEventType subscriptionEventType, SubscriptionEvent subscriptionEvent);
}
