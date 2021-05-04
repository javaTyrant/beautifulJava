package binarysearch;

/**
 * @author lumac
 * @since 2020/7/22
 */
public class FindMinOfRotate {
    public static int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = ((r - l) / 2) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else r--;
        }
        return numbers[l];
    }

    public static void main(String[] args) {

    }
}
