package ca.ulaval.glo4003.repUL.application.vendorLocation;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.vendorLocation.infra.VendorLocationRepository;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;

import java.util.List;

public class VendorLocationService {
    private final VendorLocationRepository vendorLocationRepository;

    public VendorLocationService() {
        this.vendorLocationRepository = ServiceLocator.getInstance().getService(VendorLocationRepository.class);
    }

    public VendorLocation getVendorLocationByLocation(String location) {
        return vendorLocationRepository.getVendorLocationByLocation(location);
    }

    public int attributeCaseEmplacement(String location) {
        VendorLocation vendorLocation = vendorLocationRepository.getVendorLocationByLocation(location);
        int givenLocation = vendorLocation.attributeEmplacement();

        vendorLocationRepository.updateVendorLocation(location, vendorLocation);
        return givenLocation;
    }

    public List<VendorLocation> getVendorLocations() {
        return vendorLocationRepository.getVendorLocations();
    }
}
