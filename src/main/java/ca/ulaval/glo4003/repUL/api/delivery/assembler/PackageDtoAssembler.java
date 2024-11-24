package ca.ulaval.glo4003.repUL.api.delivery.assembler;

import ca.ulaval.glo4003.repUL.api.delivery.dto.PackageFoundResponse;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;

public class PackageDtoAssembler {

    public PackageFoundResponse toPackageFoundResponse(Package aPackage) {
        return new PackageFoundResponse(
                aPackage.getId(),
                aPackage.getDestination(),
                aPackage.getDestinationCase(),
                aPackage.getFoodBox(),
                aPackage.getDeliveryStatus()
        );
    }
}
