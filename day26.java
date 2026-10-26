import java.util.concurrent.*;

public class NumberProcessor {

    static final int SIZE = 10000000;
    static final int THREADS = 4;

    static long singleThreadedSum() {
        long sum = 0;

        for (int i = 1; i <= SIZE; i++) {
            sum += i;
        }

        return sum;
    }

    static class SumTask implements Callable<Long> {
        private final int start;
        private final int end;

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Long call() {
            long sum = 0;

            for (int i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }
    }

    static long multiThreadedSum() throws Exception {
        ExecutorService executor =
                Executors.newFixedThreadPool(THREADS);

        try {
            Future<Long>[] results = new Future[THREADS];
            int chunk = SIZE / THREADS;

            for (int i = 0; i < THREADS; i++) {
                int start = i * chunk + 1;
                int end = (i == THREADS - 1)
                        ? SIZE : (i + 1) * chunk;

                results[i] = executor.submit(
                        new SumTask(start, end));
            }

            long total = 0;

            for (Future<Long> result : results) {
                total += result.get();
            }

            return total;
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        long singleResult = singleThreadedSum();
        long singleTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        long multiResult = multiThreadedSum();
        long multiTime = System.nanoTime() - startTime;

        System.out.println("Single-threaded Result: "
                + singleResult);
        System.out.println("Single-threaded Time: "
                + singleTime / 1_000_000.0 + " ms");

        System.out.println("Multithreaded Result: "
                + multiResult);
        System.out.println("Multithreaded Time: "
                multiTime / 1_000_000.0 + " ms");

        System.out.println("Results Match: "
                + (singleResult == multiResult));
    }
}