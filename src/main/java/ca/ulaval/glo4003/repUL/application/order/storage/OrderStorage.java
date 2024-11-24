package ca.ulaval.glo4003.repUL.application.order.storage;

import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.List;

public interface OrderStorage {
    void saveOrder(Order order);

    void saveOrders(List<Order> orders);

    List<Order> getAllOrder();

    List<Order> getAllOrderByEmail(String email);

    List<Order> getOrdersByIds(List<String> ids);

    Order getOrderById(String id);
}
