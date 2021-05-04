package sort;

/**
 * @author lumac
 * @since 2020/7/17
 */
public class demo {
    public static void main(String[] args) {
        demo demo = new demo();
        int[] arr = {3, 1, 2, 5, 7, 4, 6};
        /**
         *
         */
        System.out.println(demo.findKthLargest(arr, 2));
    }

    //感觉是最优美的代码
    public int findKthLargest(int[] nums, int k) {
        //为什么k要减1呢,数组的第几位数字吧,更加的直观
        return quickSort(nums, 0, nums.length - 1, k - 1);
    }
    //为什么方法抽出来之后,会查12毫秒呢?
    public int quickSort(int[] arr, int low, int high, int k) {
        System.out.println("low:" + low + "--" + "high:" + high);
        if (low >= high) return arr[low];
        int i = low - 1;//i是向后搜索指针，为简化计算先-1
        int j = high + 1;//j是向前搜索指针，为简化计算下+1
        int mid = arr[low + high >> 1];
        j = getPartition(arr, i, j, mid);
        if (j >= k) { //第k大的元素位于基准左侧
            return quickSort(arr, low, j, k);
        } else { //第k大的元素位于右侧
            return quickSort(arr, j + 1, high, k);
        }
    }

    private int getPartition(int[] arr, int i, int j, int mid) {
        while (i < j) {
            do {
                i++;
            } while (arr[i] > mid);//arr[i] 大于基准，不用交换，继续向后搜索
            do {
                j--;
            } while (arr[j] < mid);//arr[j] 小于基准，不用交换，继续向前搜索
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return j;
    }

}
