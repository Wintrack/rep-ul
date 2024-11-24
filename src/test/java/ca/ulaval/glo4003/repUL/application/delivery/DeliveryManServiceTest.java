package ca.ulaval.glo4003.repUL.application.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.auth.DeliveryManAuth;
import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryManDto;
import ca.ulaval.glo4003.repUL.application.delivery.factory.DeliveryManFactory;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryManStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeliveryManServiceTest {
    private DeliveryManStorage deliveryManStorage;

    private DeliveryManAuth deliveryManAuth;

    private DeliveryManFactory deliveryManFactory;

    private DeliveryManService deliveryManService;

    @BeforeEach
    void beforeEach() {
        deliveryManStorage = mock();
        deliveryManAuth = mock();
        deliveryManFactory = mock();

        ServiceLocator.getInstance().registerService(DeliveryManStorage.class, deliveryManStorage);
        ServiceLocator.getInstance().registerService(DeliveryManAuth.class, deliveryManAuth);
        ServiceLocator.getInstance().registerService(DeliveryManFactory.class, deliveryManFactory);

        deliveryManService = new DeliveryManService();
    }

    @Test
    void givenDeliveryManService_whenRegister_thenDelegateToServices() {
        // GIVEN
        CreateDeliveryManDto createDeliveryManDto = mock();

        // WHEN
        deliveryManService.register(createDeliveryManDto);

        // THEN
        verify(deliveryManFactory).toDeliveryMan(createDeliveryManDto);
        verify(deliveryManAuth).register(any(), any());
        verify(deliveryManStorage).createDeliveryMan(any());
    }
}