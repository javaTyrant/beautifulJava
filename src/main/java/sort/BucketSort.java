package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020-05-11
 */
public class BucketSort {
    //请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        // 根据鸽巢定理，桶的个数为n+1的时候，把n个数放入桶中，一定会产生一个空桶, 这样就可以确定最大间距出现在相邻桶之间（跳过空桶）
        int bucketCount = len + 1;
        List<Integer>[] bucket = new ArrayList[bucketCount];
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) {
            return 0;
        }

        // 每个桶的大小
        int interval = (int) Math.ceil((max - min) * 1.0 / bucketCount) + 1;

        // 将数组放到各个桶中
        for (int num : nums) {
            int bucketIndex = (num - min) / interval;
            if (bucket[bucketIndex] == null) {
                bucket[bucketIndex] = new ArrayList<>();
            }
            bucket[bucketIndex].add(num);
        }

        //先求第一个桶的最大值
        int prevBucketMax = 0;
        for (Integer num : bucket[0]) {
            prevBucketMax = Math.max(prevBucketMax, num);
        }
        int anxMax = 0;
        for (int i = 1; i < bucketCount; i++) {
            // 求每个桶的最大最小值
            if (bucket[i] == null || bucket[i].isEmpty()) {
                continue;
            }

            int bucketMin = Integer.MAX_VALUE;
            int bucketMax = 0;
            for (Integer num : bucket[i]) {
                bucketMin = Math.min(bucketMin, num);
                bucketMax = Math.max(bucketMax, num);
            }

            int diff = bucketMin - prevBucketMax;
            anxMax = Math.max(anxMax, diff);
            prevBucketMax = bucketMax;
        }

        return anxMax;
    }
}
