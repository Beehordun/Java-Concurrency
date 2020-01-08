package SynchronizedMutableDataSharing;

public class ThreadSafeArrayWriter implements Runnable {

    private final ThreadSafeSimpleArray sharedArray;
    private final int startValue;

    public ThreadSafeArrayWriter(ThreadSafeSimpleArray sharedSimpleArray, int startValue) {
        this.sharedArray = sharedSimpleArray;
        this.startValue = startValue;
    }

    @Override
    public void run() {
        for (int i = startValue; i < startValue + 3; i++) {
            sharedArray.add(i);
        }
    }
}

