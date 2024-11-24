package ca.ulaval.glo4003.repUL.application.order.storage;

import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.List;

public interface WaitingOrderQueue {
    void addWaitingOrder(Order order);

    List<Order> getWaitingOrders();

    void updateWaitingOrders(List<Order> orders);
}
