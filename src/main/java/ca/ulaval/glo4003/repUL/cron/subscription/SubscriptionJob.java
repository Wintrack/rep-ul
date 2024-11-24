package ca.ulaval.glo4003.repUL.cron.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.subscription.SubscriptionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class SubscriptionJob implements Job {
    private final SubscriptionService subscriptionService;

    public SubscriptionJob() {
        this.subscriptionService = ServiceLocator.getInstance().getService(SubscriptionService.class);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        subscriptionService.updateSubscriptionNotConfirmed();
    }
}
