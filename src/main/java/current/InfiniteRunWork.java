package current;

/**
 * @author lumac
 * @since 2021/1/29
 */
public class InfiniteRunWork {
    static class Worker implements Runnable {
        Runnable firstTask;
        final Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    private static void runWorker(Worker worker) {
        Runnable task = worker.firstTask;
        while (task != null) {
            task.run();
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker(() -> System.out.println("hello" + Thread.currentThread().getName()));
        worker.thread.start();
    }
}
