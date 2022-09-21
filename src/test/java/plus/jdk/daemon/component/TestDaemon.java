package plus.jdk.daemon.component;

import plus.jdk.daemon.annotation.DaemonComponent;

@DaemonComponent
public class TestDaemon implements Runnable {

    @Override
    public void run() {
        // do something
    }
}
