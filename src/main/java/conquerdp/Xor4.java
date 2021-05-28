package conquerdp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/5/25
 **/
public class Xor4 {
    //因此我们将这个一维的输入看成二维，从而将问题转化为：使得最终每列相等，同时「首行」的异或值为 0的最小修改次数。
    public int minChanges2(int[] nums, int k) {
        //
        int n = nums.length;
        //
        int max = 1024;
        //定义f[i][xor]为考虑前i列，且首行前i列异或值为 xor 的最小修改次数，最终答案为f[k−1][0]。
        int[][] f = new int[k][max];
        //数组来记录前一列的最小状态值。
        int[] g = new int[k];
        for (int i = 0; i < k; i++) {
            //1061109567
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            //
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            if (i == 0) { // 第 0 列：只需要考虑如何将该列变为 xor 即可
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else { // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    f[i][xor] = g[i - 1] + cnt; // 整列替换
                    for (int cur : map.keySet()) { // 部分替换
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }

    //3,4,5,2,1,7,3,4,7
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 2, 1, 7, 3, 4, 7};
        //int[] arr = {1, 2, 4, 1, 2, 5, 1, 2, 6};
        Xor4 solution = new Xor4();
        System.out.println(solution.minChanges2(arr, 3));
    }

    //给你一个整数数组 nums 和一个整数 k 。
    //区间 [left, right]（left <= right）的 异或结果 是对下标位于left 和 right（包括 left 和 right ）
    //之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
    //返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;

    //
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件 f(-1,0)=0
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            // 第 i 个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0) + 1);
                ++size;
            }
            // 求出 t2
            int t2min = Arrays.stream(f).min().getAsInt();
            int[] g = new int[MAXX];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAXX; ++mask) {
                // t1 则需要枚举 x 才能求出
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }
            // 别忘了加上 size
            for (int j = 0; j < MAXX; ++j) {
                g[j] += size;
            }
            f = g;
        }
        return f[0];
    }
}

