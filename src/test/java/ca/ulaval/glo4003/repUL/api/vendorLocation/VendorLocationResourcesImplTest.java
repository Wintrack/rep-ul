package ca.ulaval.glo4003.repUL.api.vendorLocation;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.vendorLocation.dto.VendorLocationFound;
import ca.ulaval.glo4003.repUL.application.vendorLocation.VendorLocationService;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendorLocationResourcesImplTest {

    private VendorLocationResourcesImpl vendorLocationRessources;

    private VendorLocationService vendorLocationService;

    @BeforeEach
    void setup() {
        vendorLocationService = mock();
        ServiceLocator.getInstance().registerService(VendorLocationService.class, vendorLocationService);
        vendorLocationRessources = new VendorLocationResourcesImpl();
    }

    @Test
    void whenGetVendorLocations_thenReturnVendorLocationsAndOk() {
        // GIVEN
        List<VendorLocation> vendorLocations = List.of(
                new VendorLocation("UNI", "university street", 42)
        );

        when(vendorLocationService.getVendorLocations())
                .thenReturn(vendorLocations);
        // WHEN
        Response response = vendorLocationRessources.getVendorLocations();

        // THEN
        List<VendorLocationFound> expectedVendorLocationFounds = List.of(
                new VendorLocationFound(
                        vendorLocations.get(0).getLocation(),
                        vendorLocations.get(0).getName(),
                        vendorLocations.get(0).getCapacity()
                )
        );


        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getEntity()).isEqualTo(expectedVendorLocationFounds);
    }


}