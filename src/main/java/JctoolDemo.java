import org.jctools.maps.NonBlockingHashMap;

/**
 * @author lumac
 * @since 2021/3/6
 */
public class JctoolDemo {
    public static void main(String[] args) {
        NonBlockingHashMap<Integer, Integer> map = new NonBlockingHashMap<>();
        map.put(1, 2);
    }
}
