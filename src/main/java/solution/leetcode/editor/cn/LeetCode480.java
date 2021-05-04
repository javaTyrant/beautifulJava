package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode480 {
    static class Solution2 {
        public static void main(String[] args) {
            Solution2 solution = new Solution2();
            int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
            System.out.println(Arrays.toString(solution.medianSlidingWindow(arr, 3)));
        }

        //双优先队列 + 延迟删除
        public double[] medianSlidingWindow(int[] nums, int k) {
            DualHeap dh = new DualHeap(k);
            for (int i = 0; i < k; ++i) {
                dh.insert(nums[i]);
            }
            double[] ans = new double[nums.length - k + 1];
            ans[0] = dh.getMedian();
            for (int i = k; i < nums.length; ++i) {
                dh.insert(nums[i]);
                dh.erase(nums[i - k]);
                ans[i - k + 1] = dh.getMedian();
            }
            return ans;
        }
    }

    static class DualHeap {
        // 大根堆，维护较小的一半元素
        private PriorityQueue<Integer> small;
        // 小根堆，维护较大的一半元素
        private PriorityQueue<Integer> large;
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        private Map<Integer, Integer> delayed;

        private int k;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
        private int smallSize, largeSize;

        public DualHeap(int k) {
            this.small = new PriorityQueue<>(Comparator.reverseOrder());
            this.large = new PriorityQueue<>(Comparator.naturalOrder());
            this.delayed = new HashMap<>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        //插入
        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        //清除
        public void erase(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                --smallSize;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                --largeSize;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if (delayed.get(num) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                // small 比 large 元素多 2 个
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                // small 堆顶元素被移除，需要进行 prune
                prune(small);
            } else if (smallSize < largeSize) {
                // large 比 small 元素多 1 个
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                // large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] window = new double[nums.length - k + 1];
            if (nums.length == 0) {
                return new double[0];
            }
            if (nums.length == 1) {
                return new double[]{nums[0]};
            }
            int[] arr = new int[k];
            //先维护一个有序的窗口
            if (k > 0) {
                System.arraycopy(nums, 0, arr, 0, k);
            }
            Arrays.sort(arr);
            //计算k是奇数还是偶数
            boolean even = k % 2 == 0;
            for (int i = k; i <= nums.length; i++) {
                window[i - k] = even ? arr[k / 2] / 2d + arr[k / 2 - 1] / 2d : arr[k / 2];
                if (i == nums.length) {
                    continue;
                }
                int in = nums[i];
                int addIndex = findIndex(in, arr);
                int removeIndex = findIndex(nums[i - k], arr);
                //进行插入和移除操作
                if (addIndex == removeIndex) {
                    arr[removeIndex] = in;
                } else if (removeIndex < addIndex) {
                    //把两个index之间的数移过来不包括addIndex上的数,因为in必定是小于nums[addIndex]的
                    if (addIndex - 1 - removeIndex >= 0) {
                        System.arraycopy(arr, removeIndex + 1, arr, removeIndex, addIndex - 1 - removeIndex);
                    }
                    arr[addIndex - 1] = in;
                } else {
                    //右移
                    if (removeIndex - addIndex >= 0) {
                        System.arraycopy(arr, addIndex, arr, addIndex + 1, removeIndex - addIndex);
                    }
                    arr[addIndex] = in;
                }
            }
            return window;
        }


        private int findIndex(int in, int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (arr[mid] < in) {
                    left = mid + 1;
                } else if (arr[mid] > in) {
                    right = mid - 1;
                } else if (arr[mid] == in) {
                    return mid;
                }
            }
            return left;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
