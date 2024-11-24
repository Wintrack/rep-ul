package ca.ulaval.glo4003.init.binder;

import ca.ulaval.glo4003.repUL.api.delivery.DeliveryResource;
import ca.ulaval.glo4003.repUL.api.foodbox.FoodBoxResourceImpl;
import ca.ulaval.glo4003.repUL.api.notification.NotificationResourceImpl;
import ca.ulaval.glo4003.repUL.api.order.OrderPreparerResource;
import ca.ulaval.glo4003.repUL.api.order.OrderResourceImpl;
import ca.ulaval.glo4003.repUL.api.student.StudentResource;
import ca.ulaval.glo4003.repUL.api.subscription.SubscriptionResourceImpl;
import ca.ulaval.glo4003.repUL.api.vendorLocation.VendorLocationResourcesImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ResourcesBinder extends AbstractBinder {
    @Override
    public void configure() {
        bind(new StudentResource()).to(StudentResource.class);
        bind(new FoodBoxResourceImpl()).to(FoodBoxResourceImpl.class);
        bind(new VendorLocationResourcesImpl()).to(VendorLocationResourcesImpl.class);
        bind(new SubscriptionResourceImpl()).to(SubscriptionResourceImpl.class);
        bind(new OrderResourceImpl()).to(OrderResourceImpl.class);
        bind(new OrderPreparerResource()).to(OrderPreparerResource.class);
        bind(new DeliveryResource()).to(DeliveryResource.class);
        bind(new NotificationResourceImpl()).to(NotificationResourceImpl.class);
    }
}
