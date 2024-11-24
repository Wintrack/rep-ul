package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package;

import ca.ulaval.glo4003.repUL.domain.delivery.exception.AbortPackageDeliveryException;
import ca.ulaval.glo4003.repUL.domain.delivery.exception.SetStatusOnGoingException;
import ca.ulaval.glo4003.repUL.domain.delivery.status.DeliveryStatus;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;

public class Package {
    private String id;

    private String destination;

    private int destinationCase;

    private FoodBox foodBox;

    private DeliveryStatus deliveryStatus;

    private String userEmail;

    public Package(String destination, FoodBox foodBox, String userEmail) {
        this.id = null;
        this.destinationCase = 0;
        this.destination = destination;
        this.foodBox = foodBox;
        this.userEmail = userEmail;
        this.deliveryStatus = DeliveryStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public FoodBox getFoodBox() {
        return foodBox;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getDestinationCase() {
        return destinationCase;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDestinationCase(int destinationCase) {
        this.destinationCase = destinationCase;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setPackageOnGoing() {
        if (deliveryStatus == DeliveryStatus.DELIVERED) {
            throw new SetStatusOnGoingException();
        }
        deliveryStatus = DeliveryStatus.ON_GOING;
    }

    public void abortDelivery() {
        if (deliveryStatus == DeliveryStatus.PENDING) {
            throw new AbortPackageDeliveryException();
        }
        deliveryStatus = DeliveryStatus.ON_GOING;
    }
}
