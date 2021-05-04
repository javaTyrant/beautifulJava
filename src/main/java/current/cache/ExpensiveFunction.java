package current.cache;

import java.math.BigInteger;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        Thread.sleep(1000);
        return new BigInteger(arg);
    }
}
