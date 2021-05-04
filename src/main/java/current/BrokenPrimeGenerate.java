package current;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lumac
 * @since 2020/11/18
 */
public class BrokenPrimeGenerate extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeGenerate(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {

        }
    }

    public void cancel() {
        cancelled = true;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(612);
        BrokenPrimeGenerate producer = new BrokenPrimeGenerate(primes);
        producer.start();
        int i = 100;
        try {
            while (i > 0) {
                Thread.sleep(100);
                System.out.println(primes.take());
                i--;
            }
        } finally {
            producer.cancel();
        }
    }
}
