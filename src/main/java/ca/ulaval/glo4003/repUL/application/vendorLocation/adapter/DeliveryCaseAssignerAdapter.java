package ca.ulaval.glo4003.repUL.application.vendorLocation.adapter;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.vendorLocation.VendorLocationService;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.case_assigner.CaseAssigner;

public class DeliveryCaseAssignerAdapter implements CaseAssigner {
    private final VendorLocationService vendorLocationService;

    public DeliveryCaseAssignerAdapter() {
        vendorLocationService = ServiceLocator.getInstance().getService(VendorLocationService.class);
    }

    @Override
    public int attributeCaseEmplacement(String location) {
        return vendorLocationService.attributeCaseEmplacement(location);
    }
}
