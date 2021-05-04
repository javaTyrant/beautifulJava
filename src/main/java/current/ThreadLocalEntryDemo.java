package current;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lumac
 * @since 2020/11/6
 */
public class ThreadLocalEntryDemo {
    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }

    @Builder
    @Data
    static class HorseDog {
        private int dickLen;
        private String last;
        private String name;
    }

    public static int majorityElement(int[] nums) {
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

    //分治法的精髓
    public static int majorityElementDivide(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(5);
        integers.retainAll(list1);
        System.out.println(integers);
        int[] arr = {3, 3, 1, 2, 4};
        System.out.println(majorityElement(arr));
        List<HorseDog> list = Arrays.asList(
                HorseDog
                        .builder()
                        .dickLen(2)
                        .last("ma")
                        .name("yun")
                        .build(),
                HorseDog
                        .builder()
                        .dickLen(18)
                        .last("lu")
                        .name("doug")
                        .build()
                ,
                HorseDog
                        .builder()
                        .dickLen(1)
                        .last("ma")
                        .name("yun")
                        .build());
        Function<HorseDog, String> function = a -> a.getName() + a.getLast();
        Map<String, List<HorseDog>> map = list
                .stream()
                .collect(Collectors.groupingBy(function, Collectors.toList()));
        System.out.println(map);


//        System.out.println(resizeStamp(222));
//        //32位10
//        System.out.println(Integer.numberOfLeadingZeros(1));
//        //2的15次方
//        System.out.println((1 << 15));
//        // | 这个作用
//        int[] arr = {2, 2, 3};
//        System.out.println(Arrays.toString(arr));
//
//        System.out.println(3 << 2);
    }
}
