package conquerdp;

/**
 * @author lumac
 * @since 2021/5/9
 */
public class Bo {
    //解题思路:
    //1.制作花朵最少的时间必然是 bloomDay 数组中开花所用天数最少的那朵花 min(bloomDay)
    //2.制作花朵最多的时间也只能是 bloomDay 数组中开花所需天数最多的那朵花 max(bloomDay)
    //3.寻找制作花束的最少天数必然落在上面所说的区间里 [min(bloomDay), max(bloomDay)][min(bloomDay),max(bloomDay)],
    // 连续的一个正整数区间, 因此可以通过二分查找来提升查找效率!
    //
    public int minDays(int[] bloomDay, int m, int k) {
        //先判断花够不够用
        if (k * m > bloomDay.length) {
            return -1;
        }
        int low = 1, high = 1;
        int length = bloomDay.length;
        //求出最长开花时间.
        for (int i = 0; i < length; i++) {
            high = Math.max(high, bloomDay[i]);
        }
        while (low < high) {
            int days = (high - low) / 2 + low;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    //是否能制作
    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        System.out.println("days:m:k" + days + "," + m + "," + k);
        //制作的花束的数量
        int bouquets = 0;
        //
        int flowers = 0;
        //数组的长度
        int length = bloomDay.length;
        for (int i = 0; i < length && bouquets < m; i++) {
            //需要开花的天数小于判断的天数
            if (bloomDay[i] <= days) {
                //可用花的数量+1
                flowers++;
                //如果满足了k束
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                //flower设置成0
                flowers = 0;
            }
        }
        //
        return bouquets >= m;
    }

    public static void main(String[] args) {
        //int[] arr = {1, 10, 3, 10, 2};
        int[] arr1 = {7, 7, 7, 7, 12, 7, 7};
        Bo solution = new Bo();
        //solution.minDays(arr, 3, 1);
        //需要两束,每束三朵的花.
        solution.minDays(arr1, 2, 3);
    }
}
