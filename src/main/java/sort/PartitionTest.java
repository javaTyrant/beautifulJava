package sort;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2021/2/22
 */
public class PartitionTest {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 5, -5, 6, 1};
        PartitionTest test = new PartitionTest();
        test.sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] flags = {0, 1, 1, 2, 2, 1};
        sortColors(flags);
        System.out.println(Arrays.toString(flags));
    }

    private void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int l, int r) {
        //base case别忘了
        if (l >= r) return;
        int j = partition(arr, l, r);
        sort(arr, l, j);
        sort(arr, j + 1, r);
    }

    //把数组的第一个数放到一个位置,这个位置左边的都要要小于他,右边的都要大于他
    private static int partition(int[] arr, int l, int r) {
        int num = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= num) r--;
            if (l < r) arr[l] = arr[r];
            while (l < r && arr[l] < num) l++;
            if (l < r) arr[r] = arr[l];
        }
        arr[l] = num;
        return l;
    }

    public String addStrings(String num1, String num2) {
        //计算长度
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        //carry
        int carry = 0;
        //sb来拼接答案
        StringBuilder sb = new StringBuilder();
        //只要有一个长度满足
        while (i >= 0 || j >= 0) {
            //获取数据,判断一次
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            //不足的补0
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            //求和
            int sum = a + b + carry;
            //模10
            sb.append(sum % 10);
            // /10
            carry = sum / 10;
            //共同-1
            j--;
            i--;
        }
        //看看carry还有没有值
        //append
        if (carry > 0) sb.append(carry);
        //倒序
        return sb.reverse().toString();
    }

    public String replaceSpace(String s) {
        //sb来接收数据
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                //等于则替换
                sb.append("%20");
            } else {
                //直接替换
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //三色旗:o(n)
    public static void sortColors(int[] nums) {
        //双指针,严格的说是三指针
        int low = 0, high = nums.length - 1;
        int i = 0;
        while (i <= high) {
            //如果i位置为0那么交换i和low,然后都++
            if (nums[i] == 0) {
                swap(nums, low, i);
                ++low;
                ++i;
                //如果==1
            } else if (nums[i] == 1) {
                //只加i
                ++i;
            } else if (nums[i] == 2) {
                //如果==2,i和i交换,减减
                swap(nums, high, i);
                --high;
            }
        }
    }

    private static void swap(int[] nums, int low, int i) {
        int tmp = nums[i];
        nums[i] = nums[low];
        nums[low] = tmp;
    }
}
