package ca.ulaval.glo4003.init.registry;

import ca.ulaval.glo4003.init.binder.ResourcesBinder;
import ca.ulaval.glo4003.init.registry.auth.AuthRegistry;
import ca.ulaval.glo4003.init.registry.delivery.DeliveryRegistry;
import ca.ulaval.glo4003.init.registry.foodbox.FoodboxRegistry;
import ca.ulaval.glo4003.init.registry.notification.NotificationRegistry;
import ca.ulaval.glo4003.init.registry.order.OrderRegistry;
import ca.ulaval.glo4003.init.registry.subscription.SubscriptionRegistry;
import ca.ulaval.glo4003.init.registry.user.UserRegistry;
import ca.ulaval.glo4003.init.registry.vendorLocation.VendorLocationRegistry;
import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class AppRegistry {
    private final AuthRegistry authRegistry;

    private final UserRegistry userRegistry;

    private final VendorLocationRegistry vendorLocationRegistry;

    private final FoodboxRegistry foodboxRegistry;

    private final SubscriptionRegistry subscriptionRegistry;

    private final OrderRegistry orderRegistry;

    private final DeliveryRegistry deliveryRegistry;

    private final NotificationRegistry notificationRegistry;

    public AppRegistry() {
        authRegistry = ServiceLocator.getInstance().getService(AuthRegistry.class);
        userRegistry = ServiceLocator.getInstance().getService(UserRegistry.class);
        vendorLocationRegistry = ServiceLocator.getInstance().getService(VendorLocationRegistry.class);
        foodboxRegistry = ServiceLocator.getInstance().getService(FoodboxRegistry.class);
        subscriptionRegistry = ServiceLocator.getInstance().getService(SubscriptionRegistry.class);
        orderRegistry = ServiceLocator.getInstance().getService(OrderRegistry.class);
        deliveryRegistry = ServiceLocator.getInstance().getService(DeliveryRegistry.class);
        notificationRegistry = ServiceLocator.getInstance().getService(NotificationRegistry.class);
    }


    public void registerServices() {
        authRegistry.register();
        userRegistry.register();
        vendorLocationRegistry.register();
        foodboxRegistry.register();
        subscriptionRegistry.register();
        orderRegistry.register();
        notificationRegistry.register();
        deliveryRegistry.register();
    }

    public void initCronJob() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        OrderRegistry.registerCron(scheduler);
        SubscriptionRegistry.registerCron(scheduler);
        scheduler.start();
    }

    public ResourceConfig registerConfig() {
        final ResourceConfig config = new ResourceConfig();
        config.register(new ResourcesBinder());
        config.register(ServiceLocator.getInstance().getService(AuthenticationFilter.class));
        config.packages("ca.ulaval.glo4003.repUL.api");

        return config;
    }
}
