package plus.jdk.scheduled.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import plus.jdk.scheduled.annotation.EnableScheduled;
import plus.jdk.scheduled.config.ScheduledConfig;

@Slf4j
@Configuration
@EnableScheduled
@ConditionalOnProperty(prefix = "plus.jdk.scheduled", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(ScheduledConfig.class)
public class ScheduledAutoConfiguration {

}
