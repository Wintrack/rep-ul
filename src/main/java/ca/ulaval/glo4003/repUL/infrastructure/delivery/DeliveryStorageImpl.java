package ca.ulaval.glo4003.repUL.infrastructure.delivery;

import ca.ulaval.glo4003.repUL.application.delivery.storage.DeliveryStorage;
import ca.ulaval.glo4003.repUL.domain.delivery.Delivery;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;
import ca.ulaval.glo4003.repUL.infrastructure.delivery.exception.DeliveryNotFoundException;

import java.util.*;
import java.util.Map.Entry;

public class DeliveryStorageImpl implements DeliveryStorage {

    private final HashMap<String, Delivery> deliveryHashMap = new HashMap<>();

    @Override
    public Delivery createDelivery(Delivery delivery) {
        delivery.setId(UUID.randomUUID().toString());
        delivery.getPackages().forEach(aPackage -> aPackage.setId(UUID.randomUUID().toString()));
        deliveryHashMap.put(delivery.getId(), delivery);
        return delivery;
    }

    @Override
    public List<Delivery> findUserDelivery() {
        return deliveryHashMap.values().stream().toList();
    }

    @Override
    public List<Delivery> findAllDeliveriesByUserEmail(String userEmail) {
        List<Delivery> deliveries = new ArrayList<>();

        for (Entry<String, Delivery> mapEntry : deliveryHashMap.entrySet()) {
            getUserPackages(userEmail, mapEntry.getValue())
                    .map(deliveries::add);
        }

        return deliveries;
    }

    @Override
    public Delivery getDeliveryByIdOrThrow(String id) {
        Delivery delivery = deliveryHashMap.get(id);

        if (delivery == null) {
            throw new DeliveryNotFoundException();
        }
        return delivery;
    }

    @Override
    public Delivery getDeliveryByIdAndDeliveryManEmail(String id, String deliveryManEmail) {
        Delivery delivery = deliveryHashMap.get(id);

        if (delivery == null) {
            throw new DeliveryNotFoundException();
        }
        if (delivery.getDeliveryMan() == null) {
            throw new DeliveryNotFoundException();
        }
        if (!delivery.getDeliveryMan().getEmail().equals(deliveryManEmail)) {
            throw new DeliveryNotFoundException();
        }
        return delivery;
    }

    @Override
    public void updateDelivery(String id, Delivery delivery) {
        deliveryHashMap.put(id, delivery);
    }

    private Optional<Delivery> getUserPackages(String userEmail, Delivery delivery) {
        List<Package> usersPackage = delivery.getPackages()
                .stream().filter(aPackage -> aPackage.getUserEmail().equals(userEmail))
                .toList();

        if (usersPackage.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                new Delivery(
                        delivery.getId(),
                        delivery.getLocation(),
                        usersPackage
                )
        );
    }
}
