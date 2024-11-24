package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.application.order.storage.OrderStorage;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.exception.OrderNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderStorageImpl implements OrderStorage {
    private final HashMap<String, Order> orderHashMap;

    public OrderStorageImpl() {
        this.orderHashMap = new HashMap<>();
    }

    @Override
    public void saveOrder(Order order) {
        orderHashMap.put(order.getId(), order);
    }

    @Override
    public void saveOrders(List<Order> orders) {
        for (Order order : orders) {
            saveOrder(order);
        }
    }

    @Override
    public List<Order> getAllOrder() {
        return orderHashMap.values().stream().toList();
    }

    @Override
    public List<Order> getAllOrderByEmail(String email) {
        List<Order> orders = orderHashMap.values().stream().toList();
        List<Order> userOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getUserEmail().equals(email)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    @Override
    public List<Order> getOrdersByIds(List<String> ids) {
        List<Order> ordersFound = new ArrayList<>();

        for (String id : ids) {
            Order orderFound = orderHashMap.get(id);

            if (orderFound == null) {
                throw new OrderNotFoundException();
            }
            ordersFound.add(orderFound);
        }
        return ordersFound;
    }

    @Override
    public Order getOrderById(String id) {
        Order order = orderHashMap.get(id);

        if (order == null) {
            throw new OrderNotFoundException();
        }
        return order;
    }
}
