package UnsychronizedMutableDataSharing;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray {
    private static final SecureRandom generator = new SecureRandom();
    private final int[] array;
    private int writeIndex = 0;

    SimpleArray(int size) {
        array = new int[size];
    }

    void add(int value) {
        int position = writeIndex;
        try {
            Thread.sleep(generator.nextInt(500));
        } catch(InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        array[position] = value;
        System.out.printf("%s wrote %2d to element %d.%n",Thread.currentThread().getName(), value, position);

        ++writeIndex;
        System.out.printf("Next write index: %d%n", writeIndex);
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
