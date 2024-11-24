package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package;

import ca.ulaval.glo4003.repUL.domain.delivery.exception.AbortPackageDeliveryException;
import ca.ulaval.glo4003.repUL.domain.delivery.exception.SetStatusOnGoingException;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4003.repUL.domain.delivery.status.DeliveryStatus.DELIVERED;
import static ca.ulaval.glo4003.repUL.domain.delivery.status.DeliveryStatus.ON_GOING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class PackageTest {
    @Test
    void givenDeliveryInPreparation_whenSetOnGoing_theSetStatusToOnGoing() {
        // GIVEN
        Package aPackage = new Package(
                "Destination",
                mock(),
                "test@test.com"
        );

        // WHEN
        aPackage.setPackageOnGoing();

        // THEN
        assertThat(aPackage.getDeliveryStatus()).isEqualTo(ON_GOING);
    }

    @Test
    void givenDeliveryDelivered_whenSetPackageOnGoing_thenThrowSetStatusOnGoingException() {
        // GIVEN
        Package aPackage = new Package(
                "Destination",
                mock(),
                "test@test.com"
        );

        aPackage.setDeliveryStatus(DELIVERED);

        // THEN
        assertThatThrownBy(aPackage::setPackageOnGoing)
                .isInstanceOf(SetStatusOnGoingException.class)
                .hasMessageContaining("Package delivered impossible to set on going");
    }


    @Test
    void givenDeliveryOnGoing_whenAbortDelivery_thenSetDeliveryToOnGoing() {
        // GIVEN
        Package aPackage = new Package(
                "Destination",
                mock(),
                "test@test.com"
        );

        aPackage.setDeliveryStatus(ON_GOING);

        // WHEN
        aPackage.abortDelivery();

        // THEN
        assertThat(aPackage.getDeliveryStatus()).isEqualTo(ON_GOING);
    }

    @Test
    void givenDeliveryOnGoing_whenAbortDelivery_thenAbortPackageDeliveryException() {
        // GIVEN
        Package aPackage = new Package(
                "Destination",
                mock(),
                "test@test.com"
        );

        // THEN
        assertThatThrownBy(aPackage::abortDelivery)
                .isInstanceOf(AbortPackageDeliveryException.class)
                .hasMessageContaining("Impossible to abort package, package not in Delivery");
    }
}