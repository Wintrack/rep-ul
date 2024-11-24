package ca.ulaval.glo4003.repUL.api.vendorLocation.mapper;

import ca.ulaval.glo4003.repUL.api.vendorLocation.dto.VendorLocationFound;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;

public class VendorLocationDtoMapper {

    public VendorLocationFound toVendorLocationFound(VendorLocation vendorLocation) {
        return new VendorLocationFound(
                vendorLocation.getLocation(),
                vendorLocation.getName(),
                vendorLocation.getCapacity()
        );
    }
}
