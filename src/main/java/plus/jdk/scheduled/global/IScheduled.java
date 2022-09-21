package plus.jdk.scheduled.global;

import java.util.Date;

public abstract class IScheduled implements Runnable {

    protected Date nextExecutionTime;

    @Override
    public final void run() {
        if(doOnBefore()) {
            doInCronJob();
        }
        doOnSuccess();
    }

    /**
     * 执行之前做哪些事
     * @return x
     */
    protected boolean doOnBefore() {
        return true;
    };

    /**
     * 执行成功以后做那些事
     */
    protected void doOnSuccess() {};

    /**
     * 在定时任务中执行的逻辑
     */
    protected abstract void doInCronJob();
}
