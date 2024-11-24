package ca.ulaval.glo4003.repUL.application.delivery.storage;

import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;

import java.util.List;

public interface DeliveryStorage {
    Delivery createDelivery(Delivery delivery);

    List<Delivery> findUserDelivery();

    List<Delivery> findAllDeliveriesByUserEmail(String userEmail);

    Delivery getDeliveryByIdOrThrow(String id);

    Delivery getDeliveryByIdAndDeliveryManEmail(String id, String deliveryManEmail);

    void updateDelivery(String id, Delivery delivery);
}
