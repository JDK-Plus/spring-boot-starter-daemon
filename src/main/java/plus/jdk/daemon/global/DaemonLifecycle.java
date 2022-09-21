package plus.jdk.daemon.global;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import plus.jdk.daemon.config.DaemonConfig;


public class DaemonLifecycle implements SmartLifecycle {

    private boolean running = false;

    private final DaemonInitFactory daemonInitFactory;

    public DaemonLifecycle(BeanFactory beanFactory,
                           ApplicationContext applicationContext,
                           DaemonConfig properties
                           ) {
        this.daemonInitFactory = new DaemonInitFactory(beanFactory, applicationContext, properties);
    }

    @Override
    public void start() {
        running = true;
        daemonInitFactory.initializationDefinition();
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
