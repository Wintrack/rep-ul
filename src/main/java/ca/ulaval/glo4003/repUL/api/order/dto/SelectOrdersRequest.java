package ca.ulaval.glo4003.repUL.api.order.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SelectOrdersRequest(
        @NotEmpty
        List<String> orderIds
) {
}
