## Introduction

This is a springboot component that helps you quickly create daemon applications based on the `Runnable` interface.

## Import

```xml
<dependency>
    <groupId>plus.jdk</groupId>
    <artifactId>spring-boot-starter-daemon</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Example

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