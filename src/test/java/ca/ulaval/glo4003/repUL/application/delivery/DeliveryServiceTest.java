package ca.ulaval.glo4003.repUL.application.delivery;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.delivery.dto.AbortDeliveryDto;
import ca.ulaval.glo4003.repUL.application.delivery.dto.CreateDeliveryDto;
import ca.ulaval.glo4003.repUL.application.delivery.dto.DeliveryFoundDto;
import ca.ulaval.glo4003.repUL.application.delivery.dto.OnGoingDeliveryDto;
import ca.ulaval.glo4003.repUL.application.delivery.factory.DeliveryDtoFactory;
import ca.ulaval.glo4003.repUL.application.delivery.factory.DeliveryFactory;
import ca.ulaval.glo4003.repUL.application.delivery.notification.DeliveryNotification;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryManStorage;
import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryStorage;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;
import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.DeliveryPackageService;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.case_assigner.CaseAssigner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class DeliveryServiceTest {
    private DeliveryStorage deliveryStorage;

    private DeliveryManStorage deliveryManStorage;

    private DeliveryNotification deliveryNotification;

    private CaseAssigner caseAssigner;

    private DeliveryFactory deliveryFactory;

    private DeliveryDtoFactory deliveryDtoFactory;

    private DeliveryPackageService deliveryPackageService;

    private DeliveryService deliveryService;

    @BeforeEach()
    void beforeEach() {
        this.deliveryStorage = mock();
        this.deliveryManStorage = mock();
        this.deliveryNotification = mock();
        this.caseAssigner = mock();
        this.deliveryFactory = mock();
        this.deliveryDtoFactory = mock();
        this.deliveryPackageService = mock();

        ServiceLocator.getInstance().registerService(DeliveryStorage.class, deliveryStorage);
        ServiceLocator.getInstance().registerService(DeliveryManStorage.class, deliveryManStorage);
        ServiceLocator.getInstance().registerService(DeliveryNotification.class, deliveryNotification);
        ServiceLocator.getInstance().registerService(CaseAssigner.class, caseAssigner);
        ServiceLocator.getInstance().registerService(DeliveryFactory.class, deliveryFactory);
        ServiceLocator.getInstance().registerService(DeliveryDtoFactory.class, deliveryDtoFactory);
        ServiceLocator.getInstance().registerService(DeliveryPackageService.class, deliveryPackageService);

        deliveryService = new DeliveryService();
    }

    @Test
    void givenDeliveryService_whenCreateDelivery_thenDelegateToServices() {
        // GIVEN
        CreateDeliveryDto createDeliveryDto = mock();
        List<String> deliveryManEmailsList = List.of("test@test.com");
        Delivery delivery = mock();

        when(deliveryFactory.toDelivery(createDeliveryDto)).thenReturn(delivery);
        when(deliveryManStorage.findAllEmails()).thenReturn(deliveryManEmailsList);
        when(deliveryStorage.createDelivery(delivery)).thenReturn(delivery);

        // WHEN
        deliveryService.createDelivery(createDeliveryDto);

        // THEN
        verify(deliveryFactory).toDelivery(createDeliveryDto);
        verify(deliveryManStorage).findAllEmails();
        verify(deliveryPackageService).assignCasesToPackage(eq(caseAssigner), any());
        verify(deliveryStorage).createDelivery(any());
        verify(deliveryNotification).notifyCreatedToAllDeliverMan(any(), any());
    }

    @Test
    void givenDeliveryServiceAndUserEmail_whenFindUserDelivery_thenDelegateToServices() {
        // GIVEN
        String userEmail = "test@test.com";
        Delivery delivery = mock();
        List<Delivery> deliveries = List.of(delivery);
        DeliveryFoundDto deliveryFoundDto = mock();

        when(deliveryStorage.findAllDeliveriesByUserEmail(userEmail))
                .thenReturn(deliveries);
        when(deliveryDtoFactory.toDeliveryFoundDto(delivery)).thenReturn(deliveryFoundDto);

        // WHEN
        deliveryService.findUserDelivery(userEmail);

        // THEN
        verify(deliveryStorage).findAllDeliveriesByUserEmail(userEmail);
        verify(deliveryDtoFactory).toDeliveryFoundDto(delivery);
    }

    @Test
    void givenDeliveryService_whenFindUserDelivery_thenDelegateToServices() {
        // GIVEN
        Delivery delivery = mock();
        List<Delivery> deliveries = List.of(delivery);
        DeliveryFoundDto deliveryFoundDto = mock();

        when(deliveryStorage.findUserDelivery()).thenReturn(deliveries);
        when(deliveryDtoFactory.toDeliveryFoundDto(delivery)).thenReturn(deliveryFoundDto);

        // WHEN
        deliveryService.findUserDelivery();

        // THEN
        verify(deliveryStorage).findUserDelivery();
        verify(deliveryDtoFactory).toDeliveryFoundDto(delivery);
    }

    @Test
    void givenDeliveryService_whenSetAbortDelivery_thenDelegateToServices() {
        // GIVEN
        AbortDeliveryDto abortDeliveryDto = mock();
        Delivery delivery = mock();

        when(deliveryStorage.getDeliveryByIdAndDeliveryManEmail(any(), any()))
                .thenReturn(delivery);


        // WHEN
        deliveryService.setAbortDelivery(abortDeliveryDto);

        // THEN
        verify(delivery).setAbortStatus(any());
        verify(deliveryStorage).updateDelivery(any(), any());
    }

    @Test
    void givenDeliveryService_whenSetOnGoingDelivery_thenDelegateToServices() {
        // GIVEN
        OnGoingDeliveryDto onGoingDeliveryDto = mock();
        Delivery delivery = mock();
        DeliveryMan deliveryMan = mock();

        when(deliveryStorage.getDeliveryByIdOrThrow(any())).thenReturn(delivery);
        when(deliveryManStorage.findDeliveryManByEmail(any())).thenReturn(deliveryMan);

        // WHEN
        deliveryService.setOnGoingDelivery(onGoingDeliveryDto);

        // THEN
        verify(deliveryStorage).getDeliveryByIdOrThrow(any());
        verify(deliveryManStorage).findDeliveryManByEmail(any());
        verify(delivery).setOnGoingStatus(deliveryMan);
        verify(deliveryStorage).updateDelivery(any(), any());
    }
}