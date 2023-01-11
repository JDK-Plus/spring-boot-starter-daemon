package plus.jdk.scheduled.global;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import plus.jdk.scheduled.annotation.Scheduled;
import plus.jdk.scheduled.config.ScheduledConfig;
import plus.jdk.scheduled.decider.IScheduledDecider;
import plus.jdk.scheduled.model.ScheduledDefinition;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ScheduleInitFactory implements SchedulingConfigurer {

    private final BeanFactory beanFactory;

    private final ApplicationContext applicationContext;

    private final ScheduledConfig properties;

    private Set<ScheduledDefinition> scheduledDefinitions = new HashSet<>();

    public ScheduleInitFactory(BeanFactory beanFactory,
                               ApplicationContext applicationContext,
                               ScheduledConfig properties) {
        this.beanFactory = beanFactory;
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    protected void initAndRegisterTask() {
        String[] beanNames =
                this.applicationContext.getBeanNamesForAnnotation(Scheduled.class);
        for (String beanName : beanNames) {
            IScheduled beanInstance = this.applicationContext.getBean(beanName, IScheduled.class);
            Scheduled scheduled = this.applicationContext.findAnnotationOnBean(beanName, Scheduled.class);
            ScheduledDefinition scheduledDefinition = new ScheduledDefinition();
            if (scheduled == null) {
                continue;
            }
            scheduledDefinition.setScheduled(scheduled);
            scheduledDefinition.setBeanInstance(beanInstance);
            scheduledDefinitions.add(scheduledDefinition);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        initAndRegisterTask();
        for (ScheduledDefinition scheduledDefinition : scheduledDefinitions) {
            Trigger eventTrigger;
            Scheduled scheduled = scheduledDefinition.getScheduled();
            IScheduledDecider scheduledDecider = beanFactory.getBean(scheduled.decider());
            IScheduled iScheduledInstance = scheduledDefinition.getBeanInstance();
            if (!scheduledDecider.startUp(iScheduledInstance, scheduled)) {
                continue;
            }
            String expression = scheduled.expr();
            if (expression.matches("[0-9]+")) {
                long fixRate = Long.parseLong(expression);
                eventTrigger = new PeriodicTrigger(fixRate * 1000);
            } else {
                eventTrigger = new CronTrigger(expression);
            }
            Trigger finalEventTrigger = eventTrigger;
            taskRegistrar.addTriggerTask(iScheduledInstance, triggerContext -> {
                Date nextExecTime = finalEventTrigger.nextExecutionTime(triggerContext);
                iScheduledInstance.nextExecutionTime = nextExecTime;
                return nextExecTime;
            });
        }
    }
}
