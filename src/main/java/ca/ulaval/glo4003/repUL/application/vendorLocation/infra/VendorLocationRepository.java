package ca.ulaval.glo4003.repUL.application.vendorLocation.infra;

import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.exception.VendorLocationNotFoundException;

import java.util.List;

public interface VendorLocationRepository {
    void createVendorLocation(VendorLocation VendorLocation);

    void updateVendorLocation(String location, VendorLocation vendorLocation);

    VendorLocation getVendorLocationByLocation(String location) throws VendorLocationNotFoundException;

    List<VendorLocation> getVendorLocations();
}
