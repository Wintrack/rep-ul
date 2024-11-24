package ca.ulaval.glo4003.repUL.application.order.event;

public interface OrderEventSubscriber {
    void subscribe(
            OrderEventType orderEventType,
            OrderEventListener orderEventListener
    );
}
