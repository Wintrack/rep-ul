package ca.ulaval.glo4003.repUL.application.vendorLocation;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.vendorLocation.infra.VendorLocationRepository;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendorLocationServiceTest {

    private VendorLocationService vendorLocationService;

    private VendorLocationRepository vendorLocationRepository;

    @BeforeEach
    void setup() {
        vendorLocationRepository = mock();
        ServiceLocator.getInstance().registerService(VendorLocationRepository.class, vendorLocationRepository);
        vendorLocationService = new VendorLocationService();
    }

    @Test
    void givenLocation_whenGetVendorLocationByLocation_thenReturnVendorLocation() {
        // GIVEN
        VendorLocation vendorLocation = new VendorLocation("UNI", "university street", 42);

        when(vendorLocationService.getVendorLocationByLocation(vendorLocation.getLocation()))
                .thenReturn(vendorLocation);
        // WHEN
        VendorLocation vendorLocationFound = vendorLocationRepository
                .getVendorLocationByLocation(vendorLocation.getLocation());

        // THEN
        assertThat(vendorLocationFound).isEqualTo(vendorLocation);
    }

    @Test
    void whenGetVendorLocations_thenReturnVendorLocations() {
        // GIVEN
        List<VendorLocation> vendorLocations = List.of(
                new VendorLocation("UNI", "university street", 42)
        );

        when(vendorLocationRepository.getVendorLocations()).thenReturn(vendorLocations);
        // WHEN
        List<VendorLocation> vendorLocationsFound = vendorLocationService.getVendorLocations();

        // THEN
        assertThat(vendorLocationsFound).isEqualTo(vendorLocations);

    }


}