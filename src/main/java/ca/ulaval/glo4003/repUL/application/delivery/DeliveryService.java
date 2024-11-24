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
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.case_assigner.CaseAssigner;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.DeliveryPackageService;

import java.util.List;


public class DeliveryService {
    private final DeliveryStorage deliveryStorage;

    private final DeliveryManStorage deliveryManStorage;

    private final DeliveryNotification deliveryNotification;

    private final CaseAssigner caseAssigner;

    private final DeliveryFactory deliveryFactory;

    private final DeliveryDtoFactory deliveryDtoFactory;

    private final DeliveryPackageService deliveryPackageService;

    public DeliveryService() {
        this.deliveryStorage = ServiceLocator.getInstance().getService(DeliveryStorage.class);
        this.deliveryManStorage = ServiceLocator.getInstance().getService(DeliveryManStorage.class);
        this.deliveryNotification = ServiceLocator.getInstance().getService(DeliveryNotification.class);
        this.caseAssigner = ServiceLocator.getInstance().getService(CaseAssigner.class);
        this.deliveryFactory = ServiceLocator.getInstance().getService(DeliveryFactory.class);
        this.deliveryDtoFactory = ServiceLocator.getInstance().getService(DeliveryDtoFactory.class);
        this.deliveryPackageService = ServiceLocator.getInstance().getService(DeliveryPackageService.class);
    }

    public void createDelivery(CreateDeliveryDto createDeliveryDto) {
        Delivery delivery = deliveryFactory.toDelivery(createDeliveryDto);
        List<String> deliveryManEmailsList = deliveryManStorage.findAllEmails();

        deliveryPackageService.assignCasesToPackage(caseAssigner, delivery.getPackages());
        delivery = deliveryStorage.createDelivery(delivery);
        deliveryNotification.notifyCreatedToAllDeliverMan(
                deliveryManEmailsList,
                delivery.getId()
        );
    }

    public List<DeliveryFoundDto> findUserDelivery(String userEmail) {
        List<Delivery> allDeliveriesByUserEmail = deliveryStorage
                .findAllDeliveriesByUserEmail(userEmail);

        return allDeliveriesByUserEmail
                .stream().map(deliveryDtoFactory::toDeliveryFoundDto)
                .toList();
    }

    public List<DeliveryFoundDto> findUserDelivery() {
        List<Delivery> deliveries = deliveryStorage.findUserDelivery();

        return deliveries
                .stream().map(deliveryDtoFactory::toDeliveryFoundDto)
                .toList();
    }

    public void setAbortDelivery(AbortDeliveryDto abortDelivery) {
        Delivery delivery = deliveryStorage.getDeliveryByIdAndDeliveryManEmail(
                abortDelivery.id(),
                abortDelivery.deliveryManEmail()
        );

        delivery.setAbortStatus(abortDelivery.packageId());
        deliveryStorage.updateDelivery(abortDelivery.id(), delivery);
    }

    public void setOnGoingDelivery(OnGoingDeliveryDto onGoingDeliveryDto) {
        Delivery delivery = deliveryStorage.getDeliveryByIdOrThrow(onGoingDeliveryDto.id());
        DeliveryMan deliveryMan = deliveryManStorage
                .findDeliveryManByEmail(onGoingDeliveryDto.deliveryManEmail());

        delivery.setOnGoingStatus(deliveryMan);
        deliveryStorage.updateDelivery(onGoingDeliveryDto.id(), delivery);
    }
}
