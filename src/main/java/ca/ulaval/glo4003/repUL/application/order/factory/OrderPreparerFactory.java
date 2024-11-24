package ca.ulaval.glo4003.repUL.application.order.factory;

import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderPreparerDto;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;

public class OrderPreparerFactory {
    public OrderPreparer toOrderPreparer(CreateOrderPreparerDto createOrderPreparerDto) {
        return new OrderPreparer(
                createOrderPreparerDto.email(),
                createOrderPreparerDto.name(),
                createOrderPreparerDto.location()
        );
    }
}
