package conquerdp;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/5/18
 **/
public class Friends {
    public static void main(String[] args) {
        int[] arr = {3, 1, -10, 1, 1, 4, 3, 10, 1, 1};
        System.out.println(duplicateInArray(arr));
        int N = 220;
        //第i个读者喜欢的书
        int[] a = new int[N];
        //
        int[] b = new int[N];
        Scanner sc = new Scanner(System.in);
        //n 个读者 m本书.
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            //统计有多少人喜欢同一本书.
            b[a[i]]++;
        }
        for (int i = 1; i <= n; i++) {
            //减去自身的
            if (b[a[i]] != 1) System.out.println(b[a[i]] - 1);
            else System.out.println("BeiJu");
        }
    }

    //给定 nums = [2, 3, 5, 4, 3, 2, 6, 7]。
    public static int duplicateInArray(int[] nums) {
        Set<Integer> map = new HashSet<>();
        for (int n : nums) {
            if (n > nums.length - 1) {
                return -1;
            }
        }
        for (int n : nums) {
            if (map.contains(n)) {
                return n;
            } else {
                map.add(n);
            }
        }
        return -1;
    }
}
