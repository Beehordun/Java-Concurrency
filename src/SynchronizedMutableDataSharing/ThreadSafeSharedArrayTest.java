package SynchronizedMutableDataSharing;

import UnsychronizedMutableDataSharing.ArrayWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSafeSharedArrayTest {
    public static void main(String[] args) {
        ThreadSafeSimpleArray sharedSimpleArray = new ThreadSafeSimpleArray(6);

        ThreadSafeArrayWriter writer1 = new ThreadSafeArrayWriter(sharedSimpleArray, 1);
        ThreadSafeArrayWriter writer2 = new ThreadSafeArrayWriter(sharedSimpleArray, 11);


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer1);
        executorService.execute(writer2);
        executorService.shutdown();

        try {
            boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);

            if (tasksEnded) {
                System.out.printf("%n Contents of shared array: %n");
                System.out.println(sharedSimpleArray);
            } else {
                System.out.println("Timed out while waiting for tasks to finish.");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
