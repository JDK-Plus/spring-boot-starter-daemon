package plus.jdk.scheduled.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.jdk.scheduled.annotation.Scheduled;
import plus.jdk.scheduled.global.IScheduled;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledDefinition {

    /**
     * 申明bean实例的注解
     */
    private Scheduled scheduled;

    /**
     * 定义的bean实例
     */
    private IScheduled beanInstance;
}
