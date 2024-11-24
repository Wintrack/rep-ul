package ca.ulaval.glo4003.repUL.domain.delivery;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;
import ca.ulaval.glo4003.repUL.domain.delivery.exception.PackageNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DeliveryTest {
    @Test
    void givenDelivery_whenSetOngoing_thenSetDeliveryMan() {
        // GIVEN
        Package aPackage = mock();
        Delivery delivery = new Delivery(
                UUID.randomUUID().toString(),
                "DESJARDINS",
                List.of(aPackage)
        );
        DeliveryMan deliveryMan = mock();

        // WHEN
        delivery.setOnGoingStatus(deliveryMan);

        // THEN
        assertThat(delivery.getDeliveryMan()).isEqualTo(deliveryMan);
    }

    @Test
    void givenDelivery_whenSetOngoing_thenSetOnGoingOnPackages() {
        // GIVEN
        Package aPackage = mock();
        Delivery delivery = new Delivery(
                UUID.randomUUID().toString(),
                "DESJARDINS",
                List.of(aPackage)
        );
        DeliveryMan deliveryMan = mock();

        // WHEN
        delivery.setOnGoingStatus(deliveryMan);

        // THEN
        verify(aPackage, times(1)).setPackageOnGoing();
    }

    @Test
    void givenDeliveryAndAnId_whenSetAbortStatus_thenAbortPackages() {
        // GIVEN
        String packageId = UUID.randomUUID().toString();
        Package aPackage = mock();
        Delivery delivery = new Delivery(
                UUID.randomUUID().toString(),
                "DESJARDINS",
                List.of(aPackage)
        );

        when(aPackage.getId()).thenReturn(packageId);

        // WHEN
        delivery.setAbortStatus(packageId);

        // THEN
        verify(aPackage, times(1)).abortDelivery();
    }

    @Test
    void givenDeliveryAndWrongId_whenSetAbortStatus_thenThrowPackageNotFoundException() {
        // GIVEN
        String packageId = UUID.randomUUID().toString();
        Package aPackage = mock();
        Delivery delivery = new Delivery(
                UUID.randomUUID().toString(),
                "DESJARDINS",
                List.of(aPackage)
        );

        when(aPackage.getId()).thenReturn(packageId);

        // THEN
        assertThatThrownBy(() -> delivery.setAbortStatus("Wrong id"))
                .isInstanceOf(PackageNotFoundException.class)
                .hasMessageContaining("Package not found");
    }
}