package UnsychronizedMutableDataSharing;

import SynchronizedMutableDataSharing.ThreadSafeSimpleArray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {
    public static void main(String[] args) {
        SimpleArray sharedSimpleArray = new SimpleArray(6);

        ArrayWriter writer1 = new ArrayWriter(sharedSimpleArray, 1);
        ArrayWriter writer2 = new ArrayWriter(sharedSimpleArray, 11);


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
