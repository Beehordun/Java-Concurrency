package ProducerConsumer.Unsychronized;

public class UnsychronizedBuffer implements Buffer{
    private int buffer = - 1;

    @Override
    public void blockingPut(int value) throws InterruptedException {
        System.out.printf("Producer writes\t%2d", value);
        buffer = value;
    }

    @Override
    public int blockingGet() throws InterruptedException {
        System.out.printf("Consumer reads\t%2d", buffer);
        return buffer;
    }
}
