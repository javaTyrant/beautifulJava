import java.util.Arrays;

/**
 * @author lumac
 * @since 12/1/20
 */
public class NextPermutation {
    //下一个排列
    public void nextPermutation(int[] nums) {
        //为什么要减2
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            //此时的i,j有什么特性呢?
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        NextPermutation next = new NextPermutation();
        int[] arr = {1, 3, 5};
        next.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }


}
