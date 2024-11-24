package ca.ulaval.glo4003.repUL.api.delivery.dto;

import java.util.List;

public record DeliveryFoundResponse(
        String id,
        String location,
        List<PackageFoundResponse> packages
) {
}
