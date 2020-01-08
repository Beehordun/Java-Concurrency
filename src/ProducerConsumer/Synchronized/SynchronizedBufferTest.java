package ProducerConsumer.Synchronized;

import ProducerConsumer.Unsychronized.Buffer;
import ProducerConsumer.Unsychronized.Consumer;
import ProducerConsumer.Unsychronized.Producer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedBufferTest {
    public static void main(String[] args) {
        Buffer sharedLocation = new SynchronizedBuffer();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Producer(sharedLocation));
        executorService.execute(new Consumer(sharedLocation));

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
