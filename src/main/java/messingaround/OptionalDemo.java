package messingaround;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author lumac
 * @since 2020/6/11
 */
public class OptionalDemo {
    public static void main(String[] args) {
        find();
    }

    private static int find() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> first = list.stream().filter(l -> l % 2 == -1).findFirst();
//        System.out.println(first.map(a -> a + 10).get());
        Integer res = first.orElse(0);
        System.out.println(res);
        first.orElseThrow(() -> new NullPointerException("空指针哦"));
        return res;
    }
}
