package ca.ulaval.glo4003.repUL.infrastructure.vendorLocation;

import ca.ulaval.glo4003.repUL.application.vendorLocation.infra.VendorLocationRepository;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.exception.VendorLocationNotFoundException;

import java.util.HashMap;
import java.util.List;

public class VendorLocationRepositoryImpl implements VendorLocationRepository {
    private final HashMap<String, VendorLocation> vendorLocationHashMap;

    public VendorLocationRepositoryImpl() {
        this.vendorLocationHashMap = new HashMap<>();
    }

    @Override
    public void createVendorLocation(VendorLocation vendorLocation) {
        vendorLocationHashMap.put(vendorLocation.getLocation(), vendorLocation);
    }

    @Override
    public void updateVendorLocation(String location, VendorLocation vendorLocation) {
        vendorLocationHashMap.put(vendorLocation.getLocation(), vendorLocation);
    }

    @Override
    public VendorLocation getVendorLocationByLocation(String location) throws VendorLocationNotFoundException {
        VendorLocation vendorLocation = vendorLocationHashMap.get(location);

        if (vendorLocation == null) {
            throw new VendorLocationNotFoundException();
        }
        return vendorLocation;
    }

    @Override
    public List<VendorLocation> getVendorLocations() {
        return vendorLocationHashMap.values().stream().toList();
    }
}
