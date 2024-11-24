package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.application.order.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderEventManager implements OrderEventNotifier, OrderEventSubscriber {
    private final Map<OrderEventType, List<OrderEventListener>> eventMap;

    public OrderEventManager() {
        this.eventMap = new HashMap<>();
    }

    @Override
    public void notify(OrderEventType orderEventType, OrderEvent orderEvent) {
        List<OrderEventListener> orderEventListeners = eventMap.get(orderEventType);

        if (orderEventListeners == null) {
            return;
        }
        for (OrderEventListener orderEventListener: orderEventListeners) {
            orderEventListener.receiveEvent(orderEvent);
        }
    }

    @Override
    public void subscribe(OrderEventType orderEventType, OrderEventListener orderEventListener) {
        List<OrderEventListener> orderEventListeners = eventMap.get(orderEventType);

        if (orderEventListeners == null) {
            orderEventListeners = new ArrayList<>();
        }
        orderEventListeners.add(orderEventListener);
        eventMap.put(orderEventType, orderEventListeners);
    }
}
