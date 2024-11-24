package ca.ulaval.glo4003.repUL.application.order.storage;

import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;

public interface OrderPreparerStorage {
    void createOrderPreparer(OrderPreparer orderPreparer);

    OrderPreparer getOrderPreparerByEmail(String email);
}
