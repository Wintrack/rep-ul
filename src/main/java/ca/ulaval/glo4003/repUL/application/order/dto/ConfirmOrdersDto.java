package ca.ulaval.glo4003.repUL.application.order.dto;

import java.util.List;

public record ConfirmOrdersDto(
        List<String> ordersIds,
        String orderPreparerEmail
) {}
