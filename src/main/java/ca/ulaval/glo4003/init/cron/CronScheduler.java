package ca.ulaval.glo4003.init.cron;

import org.quartz.*;

import static org.quartz.JobBuilder.newJob;

public class CronScheduler {

    public static <T extends Job> void scheduleCron(
            Class<T> jobClass,
            int hoursInterval,
            Scheduler scheduler
    ) throws SchedulerException {
        JobDetail job = newJob(jobClass)
                .withIdentity(jobClass.getName())
                .build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobClass.getName())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(hoursInterval)
                        .repeatForever()
                )
                .build();

        scheduler.scheduleJob(job, trigger);
    }
}
