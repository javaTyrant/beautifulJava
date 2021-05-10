package bit;

/**
 * @author lufengxiang
 * @since 2021/5/7
 **/
public class Xor {
    //nums[i] = start + 2*i
    public static void main(String[] args) {
        Xor solution = new Xor();
        System.out.println(solution.xorOperation(5, 2));
    }

    public int xorOperation(int n, int start) {
        int s = start >> 1, e = n & start & 1;
        int ret = sumXor(s - 1) ^ sumXor(s + n - 1);
        return ret << 1 | e;
    }

    public int sumXor(int x) {
        if (x % 4 == 0) {
            return x;
        }
        if (x % 4 == 1) {
            return 1;
        }
        if (x % 4 == 2) {
            return x + 1;
        }
        return 0;
    }
}
