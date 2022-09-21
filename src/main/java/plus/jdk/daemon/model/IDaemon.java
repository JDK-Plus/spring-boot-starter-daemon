package plus.jdk.daemon.model;

public interface IDaemon extends Runnable {

    default void doInDaemon() {};

    @Override
    default void run() {
        doInDaemon();
    }
}
