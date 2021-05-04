package array;

/**
 * @author lumac
 * @since 2020/7/25
 */
public class IsStraight {
    public static boolean isStraight(int[] nums) {
        int[] a = new int[20];
        for (int num : nums) {
            a[num]++;
        }
        int k = 1;
        while (a[k] == 0) {
            k++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (a[k] != 1) {
                if (a[0] > 0 && a[k] != 1) {
                    a[0]--;
                } else {
                    return false;
                }
            }
            k++;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(isStraight(arr));
    }
}
