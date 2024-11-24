package ca.ulaval.glo4003.repUL.application.order.dto;

import java.util.List;

public record SetOrdersReadyDto(
        List<String> ordersIds,
        String orderPreparerEmail
) {}
