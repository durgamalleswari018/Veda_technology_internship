import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounterDemo {

    static class UnsafeCounter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static class SafeCounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        int incrementsPerThread = 10000;
        int expectedTotal = numberOfThreads * incrementsPerThread;

        System.out.println("Expected Final Count: " + expectedTotal + "\n");

        UnsafeCounter unsafeCounter = new UnsafeCounter();
        Thread[] unsafeThreads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCounter.increment();
                }
            });
            unsafeThreads[i].start();
        }

        for (Thread t : unsafeThreads) {
            t.join();
        }

        System.out.println("--- Thread-Unsafe Results ---");
        System.out.println("Actual Counter Value: " + unsafeCounter.getCount());
        System.out.println("Status: " + (unsafeCounter.getCount() == expectedTotal ? "Success" : "Failed (Race Condition Occurred)"));
        System.out.println();

        SafeCounter safeCounter = new SafeCounter();
        Thread[] safeThreads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    safeCounter.increment();
                }
            });
            safeThreads[i].start();
        }

        for (Thread t : safeThreads) {
            t.join();
        }

        System.out.println("--- Thread-Safe Results ---");
        System.out.println("Actual Counter Value: " + safeCounter.getCount());
        System.out.println("Status: " + (safeCounter.getCount() == expectedTotal ? "Success (Always Accurate)" : "Failed"));
    }
}
