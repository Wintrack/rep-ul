package ca.ulaval.glo4003.repUL.application.order.event;

public interface OrderEventListener {
    void receiveEvent(OrderEvent orderEvent);
}
