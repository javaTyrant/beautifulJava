package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCode215 {
    public static void main(String[] args) {
        LeetCode215 solution = new LeetCode215();
        int[] arr = {3, 2, 1, 5, 6, 4};
        System.out.println(solution.findKthLargest(arr, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k);
    }

    int quickSort(int[] nums, int l, int r, int k) {
        if (l >= r) return nums[l];
        int i = l - 1, j = r + 1;
        int mid = nums[l + r >> 1];
        while (i < j) {
            do i++; while (nums[i] < mid);
            do j--; while (nums[j] > mid);
            if (i < j) swap(nums, i, j);
        }
        if (r - j >= k) return quickSort(nums, j + 1, r, k);
        return quickSort(nums, l, j, k - (r - j));
    }

    void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    static class SolutionPriority {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> d = new PriorityQueue<>((a, b) -> b - a);
            for (int i : nums) d.add(i);
            while (k-- > 1) d.poll();
            return d.poll();
        }

        public static void main(String[] args) {
            SolutionPriority priority = new SolutionPriority();
            int[] arr = {1, 2, 3, 4, 5};
            System.out.println(priority.findKthLargest(arr, 2));
            System.out.println(arr);

        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //see GetLeastNumbers
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {3, 2, 1, 5, 6, 4};
            System.out.println(solution.findKthLargest(arr, 2));
            System.out.println(Arrays.toString(arr));
        }

        //215. 数组中的第K个最大元素,如果找第K小个元素呢
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums, 0, nums.length - 1, k - 1);
        }

        //不断的放弃
        public int quickSort(int[] arr, int low, int high, int k) {
            if (low >= high) return arr[low];
            int i = low - 1;//i是向后搜索指针，为简化计算先-1
            int j = high + 1;//j是向前搜索指针，为简化计算下+1
            int pivot = arr[(low + high) / 2];
            while (i < j) {
                //如果arr[i]大于pivot一直往右边找,直到arr[i]小与等于pivot
                do {
                    i++;
                } while (arr[i] > pivot);
                //如果arr[j]小与pivot一直左边找,直到arr[j]大于等于pivot
                do {
                    j--;
                } while (arr[j] < pivot);
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            //j的位置:当前索引区间的最大值
            //k:上面k-1了,数组是倒序排的所以k-1就是值,不断的比较就行了
            if (j >= k) { //第k大的元素位于基准左侧
                return quickSort(arr, low, j, k);
            } else { //第k大的元素位于右侧
                return quickSort(arr, j + 1, high, k);
            }
        }

        public int findKthLargestWithHeap(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
