package plus.jdk.scheduled.selector;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.jdk.scheduled.config.ScheduledConfig;
import plus.jdk.scheduled.decider.DefaultScheduledDecider;
import plus.jdk.scheduled.global.ScheduleInitFactory;

@Configuration
public class ScheduledSelector  extends WebApplicationObjectSupport implements BeanFactoryAware, WebMvcConfigurer {

    public static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ScheduledSelector.beanFactory = beanFactory;
    }

    @Bean
    public ScheduleInitFactory getScheduleInitFactory(ScheduledConfig scheduledConfig) {
        return new ScheduleInitFactory(beanFactory, getApplicationContext(), scheduledConfig);
    }

    @Bean
    public DefaultScheduledDecider getDefaultScheduledDecider() {
        return new DefaultScheduledDecider();
    }
}