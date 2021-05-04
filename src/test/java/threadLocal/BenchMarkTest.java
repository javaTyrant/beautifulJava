package threadLocal;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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
public class BenchMarkTest {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(BenchMarkTest.class.getSimpleName())
                .output("/Users/lumac/work/beautifulCode/src/main/java/solution/b.log").forks(2).build();
        new Runner(options).run();
    }

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
