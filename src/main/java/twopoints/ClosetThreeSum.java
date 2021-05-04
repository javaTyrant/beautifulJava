package twopoints;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020-05-09
 */
public class ClosetThreeSum {
    public static void main(String[] args) {

    }

    public int threeSumClosest(int[] nums, int target) {
        //保存最接近的差值
        int closestDist = Integer.MAX_VALUE;
        //res
        int closestSum = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int s = i + 1;
            int e = nums.length - 1;

            while (s < e) {
                //和
                int sum = nums[i] + nums[s] + nums[e];
                //差值
                int dist = target - sum;

                if (dist < 0) {
                    e--; // decrease sum to be closer
                } else if (dist > 0) {
                    s++; // increase sum to be closer
                } else { // dist == 0
                    return sum; // found exact target!
                }

                // 前一次的差值大于本次的差值,则更新
                if (closestDist > Math.abs(dist)) {
                    closestDist = Math.abs(dist);
                    closestSum = sum;
                }
            }
        }

        return closestSum;
    }
}
