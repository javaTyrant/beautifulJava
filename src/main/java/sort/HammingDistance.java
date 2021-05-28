package sort;

/**
 * @author lufengxiang
 * @since 2021/5/24
 **/
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        //相同的为0不同的为1
        x = x ^ y;
        y = 0;
        //求x中为1的数量
        while (x > 0) {
            //求1
            if ((x & 1) == 1) {
                //累加
                y++;
            }
            //右移一位
            x >>= 1;
        }
        return y;
    }

    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            //&都为1才是1
            //n&(n - 1)其运算结果恰为把 n 的二进制位中的最低位的 1变为 0 之后的结果。
            n &= (n - 1);
        }
        return sum;
    }

    //169. 多数元素 摩尔投票法
    //思路:
    //如果count==0 candidate等于当前的num
    //如果num == candidate + 1 否则减1
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
