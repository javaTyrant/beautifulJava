import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author lumac
 * @since 9/26/20
 */
public class BloomFilterTest {
    private static final int size = 1000000;//预计要插入多少数据

    private static final double fpp = 0.000001;//期望的误判率

    private static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
        System.out.println("误判率:" + (count / (double) size));
    }

}
