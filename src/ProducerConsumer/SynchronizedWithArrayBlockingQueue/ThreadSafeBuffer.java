package ProducerConsumer.SynchronizedWithArrayBlockingQueue;

import ProducerConsumer.Unsychronized.Buffer;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadSafeBuffer implements Buffer {
    private final ArrayBlockingQueue<Integer> buffer;

    public ThreadSafeBuffer() {
        this.buffer = new ArrayBlockingQueue<Integer>(1);
    }

    @Override
    public void blockingPut(int value) throws InterruptedException {
        buffer.put(value);
        System.out.printf("%s%2d\t%s%d%n", "Producer writes ", value, "Buffer cells occupied: ", buffer.size());
    }

    @Override
    public int blockingGet() throws InterruptedException {
        int value = buffer.take(); // remove value from buffer
        System.out.printf("%s %2d\t%s%d%n", "Consumer reads ", value, "Buffer cells occupied: ", buffer.size());
        return value;
    }
}
