package ca.ulaval.glo4003.repUL.application.delivery.factory;

import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryDto;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;

public class DeliveryFactory {
    public Delivery toDelivery(CreateDeliveryDto createDeliveryDto) {
        return new Delivery(createDeliveryDto.location(), createDeliveryDto.packages());
    }
}
