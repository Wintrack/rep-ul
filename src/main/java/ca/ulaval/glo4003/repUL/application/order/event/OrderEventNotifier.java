package ca.ulaval.glo4003.repUL.application.order.event;

public interface OrderEventNotifier {
    void notify(OrderEventType orderEventType, OrderEvent orderEvent);
}
