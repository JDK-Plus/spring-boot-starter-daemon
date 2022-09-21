package plus.jdk.daemon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "plus.jdk.daemon")
public class DaemonConfig {

    private boolean enabled = false;

}
