package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LCOF40 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //最小的k个数
        public int[] getLeastNumbers(int[] arr, int k) {
            int low = 0, high = arr.length - 1;
            //双指针
            while (low < high) {
                int i = partition(arr, low, high);
                if (i == k) {
                    return Arrays.copyOf(arr, k);
                }
                //减小high,说明在左边
                if (i > k) high = i - 1;
                //增加low,说明在右边
                if (i < k) low = i + 1;
            }
            return Arrays.copyOf(arr, k);
        }

        //找到low的索引位置,使得左边的都小于low,右边的都大于low,注意这个会改变
        //原来的数组,还是双指针
        int partition(int[] nums, int low, int high) {
            //找到pivot
            int pivot = nums[low];
            //循环入口
            while (low < high) {
                //找到右边的小于pivot位置的数
                while (low < high && nums[high] >= pivot)
                    high--;
                //交换一下,low处的数已经暂存给pivot了
                nums[low] = nums[high];
                //找到左边的大于pivot位置的数
                while (low < high && nums[low] <= pivot)
                    low++;
                //这个high已经放到low那边了,所以直接保存
                nums[high] = nums[low];
            }
            //pivot复原
            nums[low] = pivot;
            return low;
        }

        //215. 数组中的第K个最大元素
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums, 0, nums.length - 1, k - 1);
        }

        public int quickSort(int[] arr, int low, int high, int k) {
            if (low >= high) return arr[low];
            int i = low - 1;//i是向后搜索指针，为简化计算先-1
            int j = high + 1;//j是向前搜索指针，为简化计算下+1
            int pivot = arr[(low + high) / 2];
            while (i < j) {
                do {
                    i++;
                } while (arr[i] > pivot);//arr[i] 大于基准，不用交换，继续向后搜索
                do {
                    j--;
                } while (arr[j] < pivot);//arr[j] 小于基准，不用交换，继续向前搜索
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            if (j >= k) { //第k大的元素位于基准左侧
                return quickSort(arr, low, j, k);
            } else { //第k大的元素位于右侧
                return quickSort(arr, j + 1, high, k);
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {2, 1, 3, 4, -1};
            int[] arr1 = {2, 3, 1, 4, -1};
            /*
             * partition过程
             * l            h
             * 2, 3, 1, 4, -1
             * pivot = 2
             * -1 3 1 4, -1
             */
            System.out.println(solution.partition(arr1, 0, arr1.length - 1));
            System.out.println(Arrays.toString(solution.getLeastNumbers(arr, 2)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
