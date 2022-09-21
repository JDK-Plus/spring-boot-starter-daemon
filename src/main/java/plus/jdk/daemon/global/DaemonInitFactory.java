package plus.jdk.daemon.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import plus.jdk.daemon.annotation.DaemonComponent;
import plus.jdk.daemon.config.DaemonConfig;
import plus.jdk.daemon.decider.IDaemonDecider;
import plus.jdk.daemon.model.DaemonDefinition;

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
                this.applicationContext.getBeanNamesForAnnotation(DaemonComponent.class);
        for (String beanName : beanNames) {
            Runnable beanInstance = this.applicationContext.getBean(beanName, Runnable.class);
            DaemonComponent daemonComponent = this.applicationContext.findAnnotationOnBean(beanName, DaemonComponent.class);
            DaemonDefinition daemonDefinition = new DaemonDefinition();
            daemonDefinition.setDaemonComponent(daemonComponent);
            daemonDefinition.setBeanInstance(beanInstance);
            assert daemonComponent != null;
            IDaemonDecider daemonDecider = beanFactory.getBean(daemonComponent.decider());
            if(!daemonDecider.startUp()) {
                continue;
            }
            createDaemon(daemonDefinition);
        }
    }

    private void createDaemon(DaemonDefinition daemonDefinition) {
        Runnable daemon = daemonDefinition.getBeanInstance();
        DaemonComponent daemonComponent = daemonDefinition.getDaemonComponent();
        for(int i = 0; i < daemonComponent.threadNum(); i ++) {
            Thread daemonThread = new Thread(() -> {
                while (true) {
                    try {
                        daemon.run();
                    }catch (Exception e) {
                        e.printStackTrace();
                        log.error("start daemon {} failed, message:{}", daemon.getClass().getName(), e.getMessage());
                    }
                }
            });
            daemonThread.start();
        }
    }
}
