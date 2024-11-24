package ca.ulaval.glo4003.repUL.api.delivery.assembler;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.delivery.dto.DeliveryFoundResponse;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;

public class DeliveryDtoAssembler {
    private final PackageDtoAssembler packageDtoAssembler;

    public DeliveryDtoAssembler() {
        packageDtoAssembler = ServiceLocator.getInstance().getService(PackageDtoAssembler.class);
    }

    public DeliveryFoundResponse toDeliveryFoundResponse(Delivery delivery) {
        return new DeliveryFoundResponse(
                delivery.getId(),
                delivery.getLocation(),
                delivery.getPackages()
                        .stream().map(packageDtoAssembler::toPackageFoundResponse)
                        .toList()
        );
    }
}
