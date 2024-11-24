package ca.ulaval.glo4003.repUL.api.order.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AbortOrdersRequest(
        @NotEmpty
        List<String> orderIds
) {
}
