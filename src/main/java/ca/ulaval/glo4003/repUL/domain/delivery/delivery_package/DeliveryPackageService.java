package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.case_assigner.CaseAssigner;

import java.util.List;

public class DeliveryPackageService {
    public void assignCasesToPackage(CaseAssigner caseAssigner, List<Package> packages) {
        for (Package aPackage : packages) {
            int givenEmplacement = caseAssigner.attributeCaseEmplacement(aPackage.getDestination());

            aPackage.setDestinationCase(givenEmplacement);
        }
    }
}
