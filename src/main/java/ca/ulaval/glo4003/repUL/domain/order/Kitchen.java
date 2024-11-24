package ca.ulaval.glo4003.repUL.domain.order;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    public List<Order> selectOrdersOfTheDay(List<Order> waitingOrders) {
        List<Order> orderToCreate = new ArrayList<>();

        for (Order order : waitingOrders) {
            if (order.isOrderOfTheNextDay()) {
                orderToCreate.add(order);
                waitingOrders.remove(order);
            }
        }
        return orderToCreate;
    }

    public List<Order> setOrdersReady(List<Order> orders) {
        for (Order order : orders) {
            order.setOrderReady();
        }
        return orders;
    }

    public List<Order> selectOrders(List<Order> orders, OrderPreparer orderPreparer) {
        for (Order order : orders) {
            order.selectOrder(orderPreparer);
        }
        return orders;
    }

    public List<Order> confirmOrders(List<Order> orders, OrderPreparer orderPreparer) {
        for (Order order : orders) {
            order.confirmOrder(orderPreparer);
        }
        return orders;
    }

    public List<Order> abortOrders(List<Order> orders, OrderPreparer orderPreparer) {
        for (Order order : orders) {
            order.abortOrder(orderPreparer);
        }
        return orders;
    }
}
