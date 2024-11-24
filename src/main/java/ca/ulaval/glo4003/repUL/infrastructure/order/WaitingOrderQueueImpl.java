package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.application.order.storage.WaitingOrderQueue;
import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.LinkedList;
import java.util.List;

public class WaitingOrderQueueImpl implements WaitingOrderQueue {
    private List<Order> orderWaitingList;

    public WaitingOrderQueueImpl() {
        this.orderWaitingList = new LinkedList<>();
    }

    @Override
    public void addWaitingOrder(Order order) {
        orderWaitingList.add(order);
    }

    @Override
    public List<Order> getWaitingOrders() {
        return orderWaitingList;
    }

    @Override
    public void updateWaitingOrders(List<Order> orders) {
        orderWaitingList = orders;
    }
}
