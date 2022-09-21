package plus.jdk.scheduled.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import plus.jdk.daemon.decider.DefaultDaemonDecider;
import plus.jdk.daemon.decider.IDaemonDecider;
import plus.jdk.scheduled.decider.DefaultScheduledDecider;
import plus.jdk.scheduled.decider.IScheduledDecider;

import java.lang.annotation.*;

@Bean
@Service
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Scheduled {

    /**
     * 调度周期，可以为整数或字符串的cron表达式
     * cron 表达式示例:
     * {@code "0 0 * * * *"} = the top of every hour of every day.
     * <code>"*&#47;10 * * * * *"</code> = every ten seconds.
     * {@code "0 0 8-10 * * *"} = 8, 9 and 10 o'clock of every day.
     * {@code "0 0 6,19 * * *"} = 6:00 AM and 7:00 PM every day.
     * {@code "0 0/30 8-10 * * *"} = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
     * {@code "0 0 9-17 * * MON-FRI"} = on the hour nine-to-five weekdays
     * {@code "0 0 0 25 12 ?"} = every Christmas Day at midnight
     * {@code "0 0 0 L * *"} = last day of the month at midnight
     * {@code "0 0 0 L-3 * *"} = third-to-last day of the month at midnight
     * {@code "0 0 0 1W * *"} = first weekday of the month at midnight
     * {@code "0 0 0 LW * *"} = last weekday of the month at midnight
     * {@code "0 0 0 * * 5L"} = last Friday of the month at midnight
     * {@code "0 0 0 * * THUL"} = last Thursday of the month at midnight
     * {@code "0 0 0 ? * 5#2"} = the second Friday in the month at midnight
     * {@code "0 0 0 ? * MON#1"} = the first Monday in the month at midnight
     *
     * 或数字格式，代表执行间隔.
     * @return x
     */
    String value();

    /**
     * 判定当前实例是否添加定时任务, 若startUp函数返回false，则后续不再执行
     * @return x
     */
    Class<? extends IScheduledDecider> decider() default DefaultScheduledDecider.class;
}
