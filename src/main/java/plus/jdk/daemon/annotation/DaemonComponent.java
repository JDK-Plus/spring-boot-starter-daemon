package plus.jdk.daemon.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import plus.jdk.daemon.decider.DefaultDaemonDecider;
import plus.jdk.daemon.decider.IDaemonDecider;

import java.lang.annotation.*;

@Bean
@Service
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DaemonComponent {

    /**
     * 需要创建几个线程来执行
     */
    int threadNum() default 1;

    /**
     * 判断是否启动服务，默认开启
     */
    Class<? extends IDaemonDecider> decider() default DefaultDaemonDecider.class;
}