package plus.jdk.daemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.jdk.daemon.annotation.DaemonComponent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DaemonDefinition {

    /**
     * 申明bean实例的注解
     */
    private DaemonComponent daemonComponent;

    /**
     * 定义的bean实例
     */
    private Runnable beanInstance;
}
