import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/7/1
 */
public class LinkhashMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>(4);
//        Map<String, String> map = new HashMap<>(4);
        map.put("语文", "语文");
        map.put("语文2", "语文");
        map.put("语文1", "语文");
        map.put("语文3", "语文");
        map.put("语文4", "语文");
        map.put("语文5", "语文");
        System.out.println(map);
    }
}
