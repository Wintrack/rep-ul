package ca.ulaval.glo4003.repUL.application.order.factory;

import ca.ulaval.glo4003.repUL.application.order.dto.CreateOrderDto;
import ca.ulaval.glo4003.repUL.domain.order.Order;

public class OrderFactory {
    public Order toOrder(CreateOrderDto createOrderDto) {
        return new Order(
                createOrderDto.userEmail(),
                createOrderDto.destination(),
                createOrderDto.foodBox(),
                createOrderDto.deliveryDateTime(),
                createOrderDto.orderType()
        );
    }
}
