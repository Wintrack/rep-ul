package ca.ulaval.glo4003.repUL.api.order.dto;

import ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record SetReadyOrAssembledRequest(
        @NotNull
        OrderStatus orderStatus
) {
}
