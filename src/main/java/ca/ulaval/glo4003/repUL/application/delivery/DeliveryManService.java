package ca.ulaval.glo4003.repUL.application.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.auth.DeliveryManAuth;
import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryManDto;
import ca.ulaval.glo4003.repUL.application.delivery.factory.DeliveryManFactory;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryManStorage;
import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;

public class DeliveryManService {
    private final DeliveryManStorage deliveryManStorage;
    
    private final DeliveryManAuth deliveryManAuth;
    
    private final DeliveryManFactory deliveryManFactory;

    public DeliveryManService() {
        deliveryManStorage = ServiceLocator.getInstance().getService(DeliveryManStorage.class);
        deliveryManAuth = ServiceLocator.getInstance().getService(DeliveryManAuth.class);
        deliveryManFactory = ServiceLocator.getInstance().getService(DeliveryManFactory.class);
    }

    public void register(CreateDeliveryManDto createDeliveryManDto) {
        DeliveryMan deliveryMan = deliveryManFactory.toDeliveryMan(createDeliveryManDto);

        deliveryManAuth.register(createDeliveryManDto.email(), createDeliveryManDto.password());
        deliveryManStorage.createDeliveryMan(deliveryMan);
    }
}
