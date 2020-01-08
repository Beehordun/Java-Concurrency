package ThreadExecution;

import java.security.SecureRandom;

public class PrintTask implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final int sleepTime; // random sleep time for thread
    private final String taskName;

    PrintTask(String taskName) {
        this.sleepTime = generator.nextInt(3000);
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s going to sleep for %d milliseconds on Thread %s %n", taskName, sleepTime, Thread.currentThread().getName());
            Thread.sleep(sleepTime);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        System.out.printf("%s done sleeping%n", taskName);
    }
}
