package ca.ulaval.glo4003.repUL.application.delivery.dto;

import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;

import java.util.List;

public record DeliveryFoundDto(
        String id,
        String location,
        List<Package> packages,
        DeliveryMan deliveryMan
) {
}
