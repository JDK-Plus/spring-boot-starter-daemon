package plus.jdk.scheduled.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "plus.jdk.scheduled")
public class ScheduledConfig {

    private boolean enabled = false;

}
