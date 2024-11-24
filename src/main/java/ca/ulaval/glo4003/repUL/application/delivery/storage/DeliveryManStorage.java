package ca.ulaval.glo4003.repUL.application.delivery.storage;

import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;

import java.util.List;

public interface DeliveryManStorage {
    void createDeliveryMan(DeliveryMan deliveryMan);

    DeliveryMan findDeliveryManByEmail(String email);

    List<String> findAllEmails();
}
