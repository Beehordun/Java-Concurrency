package ProducerConsumer.Synchronized;

import ProducerConsumer.Unsychronized.Buffer;

public class SynchronizedBuffer implements Buffer {
    private int buffer = -1;
    private boolean occupied = false;

    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while (occupied) {
            // output thread information and buffer information, then wait
            System.out.println("Producer tries to write."); // for demo only, IO shouldn't be performed in synchronized block
            displayState("Buffer full. Producer waits."); // for demo only
            wait();
        }

        buffer = value;
        occupied = true;
        displayState("Producer writes " + buffer); // for demo only
        notifyAll();
    }

    @Override
    public synchronized int blockingGet() throws InterruptedException {
        while(!occupied) {
            // output thread information and buffer information, then wait
            System.out.println("Consumer tries to read."); // for demo only, IO shouldn't be performed in synchronized block
            displayState("Buffer empty. Consumer waits."); // for demo only
            wait();
        }
        occupied = false;
        displayState("Consumer reads " + buffer); // for demo only
        notifyAll();
        return buffer;
    }

    private synchronized void displayState(String message) {
        System.out.printf("%-40s%d\t\t%b%n%n", message, buffer, occupied);
    }
}
