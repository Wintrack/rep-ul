package ca.ulaval.glo4003.repUL.infrastructure.vendorLocation;

import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.exception.VendorLocationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendorLocationRepositoryImplTest {

    private VendorLocationRepositoryImpl vendorLocationRepository;

    @BeforeEach
    void setup() {
        vendorLocationRepository = new VendorLocationRepositoryImpl();
    }

    @Test
    void givenCreatedVendorLocation_whenGetVendorLocations_thenReturnAllVendorLocations() {
        // GIVEN
        VendorLocation vendorLocation = new VendorLocation(
                "UNI",
                "university street",
                42
        );

        vendorLocationRepository.createVendorLocation(vendorLocation);
        // WHEN
        List<VendorLocation> vendorLocations = vendorLocationRepository.getVendorLocations();

        // THEN
        assertThat(vendorLocations).isEqualTo(List.of(vendorLocation));
    }

    @Test
    void givenLocation_whenGetVendorLocationByLocation_thenReturnVendorLocation() {
        // GIVEN
        VendorLocation vendorLocation = new VendorLocation(
                "UNI",
                "university street",
                42
        );


        vendorLocationRepository.createVendorLocation(vendorLocation);
        // WHEN
        VendorLocation vendorLocationFound = vendorLocationRepository.getVendorLocationByLocation(
                vendorLocation.getLocation()
        );

        // THEN
        assertThat(vendorLocationFound).isEqualTo(vendorLocation);
    }

    @Test
    void givenWrongLocation_whenGetVendorLocationByLocation_thenThrowVendorLocationNotFoundException() {
        // GIVEN
        String wrongLocation = "wrong location";

        // WHEN
        assertThrows(
                VendorLocationNotFoundException.class,
                () -> vendorLocationRepository.getVendorLocationByLocation(wrongLocation)
        );
    }
}