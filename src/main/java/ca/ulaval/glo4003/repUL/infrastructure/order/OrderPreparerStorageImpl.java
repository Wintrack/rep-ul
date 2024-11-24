package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.application.order.storage.OrderPreparerStorage;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;

import java.util.HashMap;
import java.util.Map;

public class OrderPreparerStorageImpl implements OrderPreparerStorage {
    Map<String, OrderPreparer> orderPreparerMap;

    public OrderPreparerStorageImpl() {
        orderPreparerMap = new HashMap<>();
    }

    @Override
    public void createOrderPreparer(OrderPreparer orderPreparer) {
        orderPreparerMap.put(orderPreparer.getEmail(), orderPreparer);
    }

    @Override
    public OrderPreparer getOrderPreparerByEmail(String email) {
        return orderPreparerMap.get(email);
    }
}
