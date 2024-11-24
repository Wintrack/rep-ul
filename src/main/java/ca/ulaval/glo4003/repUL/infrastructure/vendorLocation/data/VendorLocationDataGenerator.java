package ca.ulaval.glo4003.repUL.infrastructure.vendorLocation.data;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.vendorLocation.infra.VendorLocationRepository;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;

import java.util.List;

public class VendorLocationDataGenerator {
    private static final List<VendorLocation> DATA = List.of(
            new VendorLocation(
                    "VACHON",
                    "Entrée Vachon #1",
                    15
            ),
            new VendorLocation(
                    "PEPS",
                    "Entrée PEPS #3",
                    25
            ),
            new VendorLocation(
                    "DESJARDINS",
                    "Centre de service - Desjardins",
                    20
            ),
            new VendorLocation(
                    "VANDRY",
                    "Cours du vandry",
                    15
            ),
            new VendorLocation(
                    "MYRAND",
                    "Secteur EST",
                    12
            ),
            new VendorLocation(
                    "PYRAMIDE",
                    "Secteur Nord - Chemin Ste-foy",
                    15
            ),
            new VendorLocation(
                    "CASAULT",
                    "Secteur art",
                    14
            ),
            new VendorLocation(
                    "PLACE_STE_FOY",
                    "Secteur Ouest - Place Ste-foy",
                    15
            )
    );

    public static void createMockData() {
        VendorLocationRepository service = ServiceLocator.getInstance().getService(VendorLocationRepository.class);

        DATA.forEach(service::createVendorLocation);
    }
};
