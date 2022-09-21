package plus.jdk.scheduled.decider;

import plus.jdk.scheduled.global.IScheduled;

public interface IScheduledDecider {

    /**
     * 判断是否启动
     * @param iSchedule x
     * @return x
     */
    boolean startUp(IScheduled iSchedule);
}
