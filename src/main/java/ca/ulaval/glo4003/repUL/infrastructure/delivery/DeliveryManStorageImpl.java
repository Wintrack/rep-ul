package ca.ulaval.glo4003.repUL.infrastructure.delivery;

import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryManStorage;
import ca.ulaval.glo4003.repUL.domain.delivery.DeliveryMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryManStorageImpl implements DeliveryManStorage {
    private final Map<String, DeliveryMan> deliveryManMap;

    public DeliveryManStorageImpl() {
        deliveryManMap = new HashMap<>();
    }

    @Override
    public void createDeliveryMan(DeliveryMan deliveryMan) {
        deliveryManMap.put(deliveryMan.getEmail(), deliveryMan);
    }

    @Override
    public DeliveryMan findDeliveryManByEmail(String email) {
        return deliveryManMap.get(email);
    }

    @Override
    public List<String> findAllEmails() {
        List<String> emails = new ArrayList<>();

        for (DeliveryMan deliveryMan: deliveryManMap.values()) {
            emails.add(deliveryMan.getEmail());
        }

        return emails;
    }
}
