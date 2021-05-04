package current;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lumac
 * @since 2021/2/4
 */
@BenchmarkMode({
        Mode.AverageTime
})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Threads(50)
@Fork(1)
@State(Scope.Benchmark)
public class ThreadLocalRandomTest {
    Random random = new Random();

    ThreadLocal<Random> threadLocalRandomHolder = ThreadLocal.withInitial(Random::new);

    @Benchmark
    public int random() {
        return random.nextInt();
    }

    @Benchmark
    public int threadLocalRandom() {
        return ThreadLocalRandom.current().nextInt();
    }

    @Benchmark
    public int threadLocalRandomHolder() {
        return threadLocalRandomHolder.get().nextInt();
    }
}
