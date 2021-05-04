package current;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2020/11/18
 */
public class PrimeGenerate implements Runnable {
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerate generate = new PrimeGenerate();
        new Thread(generate).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            generate.cancel();
        }
        System.out.println(generate.get());
    }
}
