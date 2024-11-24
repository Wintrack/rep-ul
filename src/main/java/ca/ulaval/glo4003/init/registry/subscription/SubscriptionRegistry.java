package ca.ulaval.glo4003.init.registry.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.payment.infra.PaymentSystem;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventNotifier;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventSubscriber;
import ca.ulaval.glo4003.repUL.application.subscription.storage.SubscriptionStorage;
import ca.ulaval.glo4003.repUL.cron.subscription.SubscriptionJob;
import ca.ulaval.glo4003.repUL.infrastructure.payment.InMemoryPaymentSystem;
import ca.ulaval.glo4003.repUL.infrastructure.subscription.InMemorySubscriptionStorageImpl;
import ca.ulaval.glo4003.repUL.infrastructure.subscription.SubscriptionEventManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import static ca.ulaval.glo4003.init.cron.CronScheduler.scheduleCron;

public class SubscriptionRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(PaymentSystem.class, new InMemoryPaymentSystem());
        serviceLocator.registerService(SubscriptionStorage.class, new InMemorySubscriptionStorageImpl());

        SubscriptionEventManager subscriptionEventManager = new SubscriptionEventManager();
        serviceLocator.registerService(SubscriptionEventNotifier.class, subscriptionEventManager);
        serviceLocator.registerService(SubscriptionEventSubscriber.class, subscriptionEventManager);
    }



    public static void registerCron(Scheduler scheduler) throws SchedulerException {
        scheduleCron(SubscriptionJob.class, 24, scheduler);
    }
}
