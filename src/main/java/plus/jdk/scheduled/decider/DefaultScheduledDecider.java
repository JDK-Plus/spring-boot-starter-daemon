package plus.jdk.scheduled.decider;

import plus.jdk.scheduled.annotation.Scheduled;
import plus.jdk.scheduled.global.IScheduled;

public class DefaultScheduledDecider implements IScheduledDecider {
    @Override
    public boolean startUp(IScheduled iScheduled, Scheduled scheduled) {
        return true;
    }
}
