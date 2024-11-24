package ca.ulaval.glo4003.repUL.api.order.assembler;

import ca.ulaval.glo4003.repUL.api.order.dto.OrderGetResponse;
import ca.ulaval.glo4003.repUL.domain.order.Order;

public class OrderDtoAssembler {
    public OrderGetResponse toOrderGetResponse(Order order) {
        return new OrderGetResponse(
                order.getId(),
                order.getFoodBox(),
                order.getOrderStatus(),
                order.getOrderPreparator()
        );
    }
}
