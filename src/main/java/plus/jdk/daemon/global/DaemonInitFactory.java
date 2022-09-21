package plus.jdk.daemon.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import plus.jdk.daemon.annotation.Daemon;
import plus.jdk.daemon.config.DaemonConfig;
import plus.jdk.daemon.decider.IDaemonDecider;
import plus.jdk.daemon.model.DaemonDefinition;
import plus.jdk.daemon.model.IDaemon;

@Slf4j
public class DaemonInitFactory {

    private final BeanFactory beanFactory;

    private final ApplicationContext applicationContext;

    public DaemonInitFactory(BeanFactory beanFactory, ApplicationContext applicationContext,
                             DaemonConfig properties) {
        this.beanFactory = beanFactory;
        this.applicationContext = applicationContext;
    }

    protected void initializationDefinition() {
        String[] beanNames =
                this.applicationContext.getBeanNamesForAnnotation(Daemon.class);
        for (String beanName : beanNames) {
            IDaemon beanInstance = this.applicationContext.getBean(beanName, IDaemon.class);
            Daemon daemon = this.applicationContext.findAnnotationOnBean(beanName, Daemon.class);
            DaemonDefinition daemonDefinition = new DaemonDefinition();
            daemonDefinition.setDaemon(daemon);
            daemonDefinition.setBeanInstance(beanInstance);
            assert daemon != null;
            IDaemonDecider daemonDecider = beanFactory.getBean(daemon.decider());
            if(!daemonDecider.startUp()) {
                continue;
            }
            createDaemon(daemonDefinition);
        }
    }

    private void createDaemon(DaemonDefinition daemonDefinition) {
        IDaemon daemon = daemonDefinition.getBeanInstance();
        Daemon daemonComponent = daemonDefinition.getDaemon();
        for(int i = 0; i < daemonComponent.threadNum(); i ++) {
            Thread daemonThread = new Thread(() -> {
                while (true) {
                    try {
                        daemon.onBefore();
                        daemon.run();
                        daemon.onAfter();
                    }catch (Exception | Error e) {
                        e.printStackTrace();
                        log.error("start daemon {} failed, message:{}", daemon.getClass().getName(), e.getMessage());
                    }
                }
            });
            daemonThread.start();
        }
    }
}
