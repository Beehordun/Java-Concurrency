package ThreadExecution;

public class BasicThreadExecution {

    static void printThreadMessage(String message) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.printf("Thread %s: %s \n", currentThreadName, message);
    }

    private static class SimpleTask implements Runnable {
        private  String[] daysOfTheWeek = {"Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        @Override
        public void run() {
            for(String day: daysOfTheWeek) {
                try {
                    Thread.sleep(2000);
                    printThreadMessage(day);
                } catch(InterruptedException exception) {
                    printThreadMessage("I was interrupted :( \n So, I am exiting");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long waitingTime = 30 * 1000; // Waiting time of 30seconds(30,000ms)

        printThreadMessage("About to start SimpleTask Thread");
        long startTime = System.currentTimeMillis();
        Thread simpleTaskThread = new Thread(new SimpleTask());
        simpleTaskThread.start();

        printThreadMessage("SimpleTask Thread has started");

        while(simpleTaskThread.isAlive()) {
            printThreadMessage("Waiting for Simple Task Thread to finish");

            simpleTaskThread.join(1000);

            if (((System.currentTimeMillis() - startTime) > waitingTime) && simpleTaskThread.isAlive()) {
                printThreadMessage("Tired of waiting!");
                simpleTaskThread.interrupt();
                simpleTaskThread.join();
            }
        }
        printThreadMessage("Now exiting the main thread");
    }
}
