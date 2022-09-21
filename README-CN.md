
## 简介

这是一款帮助你快速基于`Runnable`接口创建 daemon 应用的springboot组件。

## 引入

```xml
<dependency>
    <groupId>plus.jdk</groupId>
    <artifactId>spring-boot-starter-daemon</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 使用示例

```java
package plus.jdk.daemon.component;

import plus.jdk.daemon.annotation.DaemonComponent;

@DaemonComponent
public class TestDaemon implements Runnable {

    @Override
    public void run() {
        // do something
    }
}
```