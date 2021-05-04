package bitsetdemo;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @author lumac
 * @since 2020/8/8
 */
public class BitsetDemo {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        //生成一百个随机数
        for (int i = 0; i < 100; i++) {
            int randomResult = random.nextInt(100);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        BitSet bitSet = new BitSet(100);
        //右移6位=数/2的6次方,一个long可以保存64个字节,而bitset是一个long数组
        //所以要知道long具体的索引
        for (int i = 0; i < 100; i++) {
            bitSet.set(list.get(i));
        }

        System.out.println("0~100不在上述随机数中有" + bitSet.cardinality() + "个");
        for (int i = 0; i < 100; i++) {
            if (!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
