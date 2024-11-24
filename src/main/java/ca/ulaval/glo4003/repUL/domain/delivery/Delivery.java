package ca.ulaval.glo4003.repUL.domain.delivery;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.Package;
import ca.ulaval.glo4003.repUL.domain.delivery.exception.PackageNotFoundException;

import java.util.List;
import java.util.Objects;

public class Delivery {
    private String id;

    private String location;

    private List<Package> packages;

    private DeliveryMan deliveryMan;

    public Delivery(
            String id,
            String location,
            List<Package> packages
    ) {
        this.id = id;
        this.location = location;
        this.packages = packages;
        this.deliveryMan = null;
    }

    public Delivery(String location, List<Package> packages) {
        this.id = null;
        this.location = location;
        this.packages = packages;
        this.deliveryMan = null;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public void setOnGoingStatus(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
        for (Package aPackage : packages) {
            aPackage.setPackageOnGoing();
        }
    }

    public void setAbortStatus(String packageId) {
        for (Package aPackage : packages) {
            if (aPackage.getId().equals(packageId)) {
                aPackage.abortDelivery();
                return;
            }
        }
        throw new PackageNotFoundException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id) &&
                Objects.equals(location, delivery.location) &&
                Objects.equals(packages, delivery.packages) &&
                Objects.equals(deliveryMan, delivery.deliveryMan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, packages, deliveryMan);
    }
}
