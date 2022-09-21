package plus.jdk.daemon.decider;

public class DefaultDaemonDecider implements IDaemonDecider {
    @Override
    public boolean startUp() {
        return true;
    }
}
