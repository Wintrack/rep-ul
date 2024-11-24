package ca.ulaval.glo4003.repUL.application.order.event;

import ca.ulaval.glo4003.repUL.domain.order.Order;

import java.util.List;

public record OrderEvent(
        List<Order> orders
) {}
