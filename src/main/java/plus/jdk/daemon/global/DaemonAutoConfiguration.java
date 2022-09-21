package plus.jdk.daemon.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import plus.jdk.daemon.annotation.EnableDaemons;
import plus.jdk.daemon.config.DaemonConfig;

@Slf4j
@Configuration
@EnableDaemons
@ConditionalOnProperty(prefix = "plus.jdk.daemon", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(DaemonConfig.class)
public class DaemonAutoConfiguration {

}
