package ca.ulaval.glo4003.repUL.application.delivery.factory;

import ca.ulaval.glo4003.repUL.application.delivery.dto.DeliveryFoundDto;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;

public class DeliveryDtoFactory {
    public DeliveryFoundDto toDeliveryFoundDto(Delivery delivery) {
        return new DeliveryFoundDto(
                delivery.getId(),
                delivery.getLocation(),
                delivery.getPackages(),
                delivery.getDeliveryMan()
        );
    }
}
