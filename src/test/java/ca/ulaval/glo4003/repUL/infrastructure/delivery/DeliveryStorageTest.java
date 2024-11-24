package ca.ulaval.glo4003.repUL.infrastructure.delivery;

import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryStorage;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryStorageTest {
    private DeliveryStorage deliveryStorage;

    private static final String ID = UUID.randomUUID().toString();

    private static final String LOCATION = "123 Main St";

    private static final List<Package> PACKAGES = List.of(
            new Package(
                    "42 avenue bob street",
                    new FoodBox(UUID.randomUUID().toString()),
                    "test@test.com"
            )
    );

    @BeforeEach
    void setUp() {
        deliveryStorage = new DeliveryStorageImpl(); // Remplacez par votre implémentation réelle
    }

    @Test
    void givenLocationAndFoodBox_whenCreateDelivery_thenCreateDelivery() {
        // GIVEN
        Delivery delivery = new Delivery(
                ID,
                LOCATION,
                PACKAGES
        );

        // WHEN
        Delivery createdDelivery = deliveryStorage.createDelivery(delivery);

        // THEN
        List<Delivery> allDeliveries = deliveryStorage.findUserDelivery();
        assertThat(allDeliveries).contains(createdDelivery);
    }
}
