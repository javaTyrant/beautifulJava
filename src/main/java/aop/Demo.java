package aop;

/**
 * @author lumac
 * @since 2021/2/21
 */
public class Demo {
    //[2,3,6,1,4,8]
    public static void main(String[] args) {

    }

    public static int findKthLargest(int[] nums, int k) {
        return sort(nums, 0, k - 1);
    }

    private static int sort(int[] nums, int left, int right) {
        return 1;
    }
}
