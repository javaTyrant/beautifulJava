/**
 * @author lumac
 * @since 2020/11/17
 */
public class MostElements {
    //169. 多数元素

    //分治法
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        //
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElementDivide(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    //Boyer-Moore投票法
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(-1 << (Integer.SIZE - 3));
        System.out.println(0 << (Integer.SIZE - 3));
        System.out.println(1 << (Integer.SIZE - 3));
        System.out.println(2 << (Integer.SIZE - 3));
        System.out.println(3 << (Integer.SIZE - 3));
        int[] arr = {3, 3, 1, 2, 3};
        MostElements most = new MostElements();
        System.out.println(most.majorityElement(arr));
        System.out.println(most.majorityElementDivide(arr));
    }
}
