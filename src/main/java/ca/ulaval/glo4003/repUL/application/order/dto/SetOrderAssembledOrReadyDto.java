package ca.ulaval.glo4003.repUL.application.order.dto;

import ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus;

public record SetOrderAssembledOrReadyDto(
        String id,
        OrderStatus orderStatus,
        String orderPreparerEmail
) {}
