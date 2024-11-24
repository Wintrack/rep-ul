package ca.ulaval.glo4003.repUL.application.delivery.notification;

import java.util.List;

public interface DeliveryNotification {
    void notifyCreatedToAllDeliverMan(List<String> emails, String id);
}
