package plus.jdk.daemon.annotation;

import org.springframework.context.annotation.Import;
import plus.jdk.daemon.selector.DaemonSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Import(DaemonSelector.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableDaemons {
}
