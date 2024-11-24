package ca.ulaval.glo4003.repUL.application.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.auth.OrderPreparerAuth;
import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderPreparerDto;
import ca.ulaval.glo4003.repUL.application.order.factory.OrderPreparerFactory;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderPreparerStorage;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;

public class OrderPreparerService {
    private final OrderPreparerStorage orderPreparerStorage;

    private final OrderPreparerFactory orderPreparerFactory;

    private final OrderPreparerAuth orderPreparerAuth;

    public OrderPreparerService() {
        orderPreparerStorage = ServiceLocator.getInstance().getService(OrderPreparerStorage.class);
        orderPreparerFactory = ServiceLocator.getInstance().getService(OrderPreparerFactory.class);
        orderPreparerAuth = ServiceLocator.getInstance().getService(OrderPreparerAuth.class);
    }

    public void createOrderPreparer(CreateOrderPreparerDto createOrderPreparerDto) {
        OrderPreparer orderPreparer = orderPreparerFactory.toOrderPreparer(createOrderPreparerDto);

        orderPreparerAuth.register(createOrderPreparerDto.email(), createOrderPreparerDto.password());
        orderPreparerStorage.createOrderPreparer(orderPreparer);

    }
}
