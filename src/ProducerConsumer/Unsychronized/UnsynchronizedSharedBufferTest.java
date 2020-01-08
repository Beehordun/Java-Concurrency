package ProducerConsumer.Unsychronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UnsynchronizedSharedBufferTest {
    public static void main(String[] args) {
        Buffer unsynchronizedBuffer = new UnsychronizedBuffer();
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed");
        System.out.printf("------\t\t-----\t---------------\t---------------%n%n");

        executorService.execute(new Producer(unsynchronizedBuffer));
        executorService.execute(new Consumer(unsynchronizedBuffer));

        executorService.shutdown();

       try {
           executorService.awaitTermination(1, TimeUnit.MINUTES);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}
