package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode347 {
    static class Solution {
        //给定一个非空的整数数组，返回其中出现频率前k高的元素。
        //示例 1:
        //输入: nums = [1,1,1,2,2,3], k = 2
        //输出: [1,2]
        //示例 2:
        //输入: nums = [1], k = 1
        //输出: [1]

        public static int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> occurrences = new HashMap<>();
            for (int num : nums) {
                occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
            }
            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                int num = entry.getKey(), count = entry.getValue();
                if (queue.size() == k) {
                    //如果队列中的次数小于当前的次数,更新
                    if (queue.peek()[1] < count) {
                        queue.poll();
                        queue.offer(new int[]{num, count});
                    }
                } else {
                    //放入队列
                    queue.offer(new int[]{num, count});
                }
            }
            int[] ret = new int[k];
            for (int i = 0; i < k; ++i) {
                ret[i] = queue.poll()[0];
            }
            return ret;
        }

        public static int[] topKFrequentBest(int[] nums, int k) {
            ArrayList<int[]> rec = new ArrayList<>();
            Arrays.sort(nums);
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    rec.add(new int[]{nums[i - 1], count});
                    count = 1;
                } else {
                    count++;
                }
            }
            rec.add(new int[]{nums[nums.length - 1], count});
            rec.sort((a, b) -> b[1] - a[1]);
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) {
                ans[i] = rec.get(i)[0];
            }
            return ans;
        }

        public static void main(String[] args) {
            int[] arr = {1, 1, 2, 2, 2, 3, 3, 3, 3};
            System.out.println(Arrays.toString(topKFrequent(arr, 2)));
            System.out.println(Arrays.toString(topKFrequentBest(arr, 2)));
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
            priorityQueue.offer(1);
            priorityQueue.offer(2);
            System.out.println(priorityQueue.poll());
            System.out.println(priorityQueue.poll());
        }
    }

}
