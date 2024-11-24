package ca.ulaval.glo4003.repUL.cron.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class OrderJob implements Job {
    private final OrderService orderService;

    public OrderJob() {
        this.orderService = ServiceLocator.getInstance().getService(OrderService.class);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        orderService.createOrdersOfTheDay();
    }
}
