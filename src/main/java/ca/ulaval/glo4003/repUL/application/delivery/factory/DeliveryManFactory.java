package ca.ulaval.glo4003.repUL.application.delivery.factory;

import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryManDto;
import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;

public class DeliveryManFactory {
    public DeliveryMan toDeliveryMan(CreateDeliveryManDto deliveryManDto) {
        return new DeliveryMan(
                deliveryManDto.email(),
                deliveryManDto.name(),
                deliveryManDto.idul(),
                deliveryManDto.location(),
                deliveryManDto.sex(),
                deliveryManDto.birthDate()
        );
    }
}
