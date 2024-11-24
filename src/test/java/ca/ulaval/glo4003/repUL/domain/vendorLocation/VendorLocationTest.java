package ca.ulaval.glo4003.repUL.domain.vendorLocation;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception.NoLockersAvailable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VendorLocationTest {
    @Test
    void givenVendorLocationWithCapacity_whenAttributeEmplacement_thenReturnALockerEmplacement() {
        // GIVEN
        VendorLocation vendorLocation = new VendorLocation(
                "DESJARDINS",
                "122 avenue desjardins",
                42
        );

        // WHEN
        int emplacement = vendorLocation.attributeEmplacement();

        // THEN
        assertThat(emplacement).isEqualTo(0);
    }

    @Test
    void givenVendorLocationWithNoCapacity_whenAttributeEmplacement_thenThrowNoLockersAvailable() {
        // GIVEN
        VendorLocation vendorLocation = new VendorLocation(
                "DESJARDINS",
                "122 avenue desjardins",
                0
        );

        // THEN
        assertThatThrownBy(vendorLocation::attributeEmplacement)
                .isInstanceOf(NoLockersAvailable.class)
                .hasMessageContaining("No locker available");
    }

}