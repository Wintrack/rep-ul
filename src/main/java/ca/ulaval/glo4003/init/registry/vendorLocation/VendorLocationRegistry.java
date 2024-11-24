package ca.ulaval.glo4003.init.registry.vendorLocation;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.vendorLocation.infra.VendorLocationRepository;
import ca.ulaval.glo4003.repUL.infrastructure.vendorLocation.VendorLocationRepositoryImpl;
import ca.ulaval.glo4003.repUL.infrastructure.vendorLocation.data.VendorLocationDataGenerator;

public class VendorLocationRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(VendorLocationRepository.class, new VendorLocationRepositoryImpl());
        VendorLocationDataGenerator.createMockData();
    }
}
