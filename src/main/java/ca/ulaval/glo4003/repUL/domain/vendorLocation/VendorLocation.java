package ca.ulaval.glo4003.repUL.domain.vendorLocation;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception.NoLockersAvailable;
import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Locker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VendorLocation {
    String location;

    String name;

    int capacity;

    Map<Integer, Locker> lockers;

    public VendorLocation(
            String location,
            String name,
            int capacity
    ) {
        this.location = location;
        this.name = name;
        this.capacity = capacity;
        this.lockers = new HashMap<>();

        for (int i = 0; i < capacity; i++) {
            lockers.put(i, new Locker());
        }
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int attributeEmplacement() {
        for (Entry<Integer, Locker> mapEntry : lockers.entrySet()) {
            if (!mapEntry.getValue().isTaken()) {
                mapEntry.getValue().takeLocker();
                return mapEntry.getKey();
            }
        }
        throw new NoLockersAvailable();
    }
}
