package plus.jdk.daemon.selector;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.jdk.daemon.config.DaemonConfig;
import plus.jdk.daemon.decider.DefaultDaemonDecider;
import plus.jdk.daemon.global.DaemonLifecycle;

@Configuration
public class DaemonSelector extends WebApplicationObjectSupport implements BeanFactoryAware, WebMvcConfigurer {

    public static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DaemonSelector.beanFactory = beanFactory;
    }

    @Bean
    public DaemonLifecycle getDaemonInitFactory(DaemonConfig daemonConfig) {
        return new DaemonLifecycle(beanFactory, getApplicationContext(), daemonConfig);
    }

    @Bean
    public DefaultDaemonDecider getDefaultDaemonDecider() {
        return new DefaultDaemonDecider();
    }
}
