package ca.ulaval.glo4003.init.registry.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventNotifier;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventSubscriber;
import ca.ulaval.glo4003.repUL.application.order.event.OrderSubscriptionEventListener;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderPreparerStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.WaitingOrderQueue;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventSubscriber;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventType;
import ca.ulaval.glo4003.repUL.cron.order.OrderJob;
import ca.ulaval.glo4003.repUL.infrastructure.order.OrderEventManager;
import ca.ulaval.glo4003.repUL.infrastructure.order.OrderPreparerStorageImpl;
import ca.ulaval.glo4003.repUL.infrastructure.order.OrderStorageImpl;
import ca.ulaval.glo4003.repUL.infrastructure.order.WaitingOrderQueueImpl;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import static ca.ulaval.glo4003.init.cron.CronScheduler.scheduleCron;

public class OrderRegistry {

    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(OrderStorage.class, new OrderStorageImpl());
        serviceLocator.registerService(WaitingOrderQueue.class, new WaitingOrderQueueImpl());
        serviceLocator.registerService(OrderPreparerStorage.class, new OrderPreparerStorageImpl());

        OrderEventManager orderEventManager = new OrderEventManager();
        serviceLocator.registerService(OrderEventNotifier.class, orderEventManager);
        serviceLocator.registerService(OrderEventSubscriber.class, orderEventManager);
        registerEventsListeners(serviceLocator);
    }

    public static void registerCron(Scheduler scheduler) throws SchedulerException {
        scheduleCron(OrderJob.class, 24, scheduler);
    }

    private static void registerEventsListeners(ServiceLocator serviceLocator) {
        SubscriptionEventSubscriber service = serviceLocator.getService(SubscriptionEventSubscriber.class);

        service.subscribe(SubscriptionEventType.SUBSCRIPTION_CONFIRMED, new OrderSubscriptionEventListener());
    }
}
