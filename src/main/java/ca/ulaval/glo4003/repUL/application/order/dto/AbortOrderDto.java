package ca.ulaval.glo4003.repUL.application.order.dto;

import java.util.List;

public record AbortOrderDto(
        List<String> ordersIds,
        String orderPreparerEmail
) {}
