package ca.ulaval.glo4003.repUL.application.delivery.dto;

public record AbortDeliveryDto(
        String id,
        String packageId,
        String deliveryManEmail
) {
}
