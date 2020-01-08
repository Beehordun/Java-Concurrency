package ProducerConsumer.Unsychronized;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Producer(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int num = 1; num <= 10; num++) {
            try {
                Thread.sleep(generator.nextInt(300));
                sharedLocation.blockingPut(num);
                sum += num;
                System.out.printf("Sum is\t%2d%n", sum);
            } catch(InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Producer done producing%nTerminating Producer%n");
    }
}
