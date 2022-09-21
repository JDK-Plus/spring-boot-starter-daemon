package plus.jdk.daemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.jdk.daemon.annotation.Daemon;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DaemonDefinition {

    /**
     * 申明bean实例的注解
     */
    private Daemon daemon;

    /**
     * 定义的bean实例
     */
    private IDaemon beanInstance;
}
