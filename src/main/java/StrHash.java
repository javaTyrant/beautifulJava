import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/5
 **/
public class StrHash {
    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        //先读一行切分开
        String[] s = read.readLine().split(" ");
        //字符串长度
        int n = Integer.parseInt(s[0]);
        //询问的次数
        int t = Integer.parseInt(s[1]);
        //读取字符串
        String w = read.readLine();
        //
        int P = 131;
        long[] h = new long[n + 10];
        long[] p = new long[n + 10];
        p[0] = 1;
        //初始化.
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + w.charAt(i - 1);
        }
        while (t-- > 0) {
            //
            s = read.readLine().split(" ");
            int l1 = Integer.parseInt(s[0]);
            int r1 = Integer.parseInt(s[1]);
            int l2 = Integer.parseInt(s[2]);
            int r2 = Integer.parseInt(s[3]);
            //
            String out = h[r1] - h[l1 - 1] * p[r1 - l1 + 1] == h[r2] - h[l2 - 1] * p[r2 - l2 + 1] ?
                    "Yes" : "No";
            log.write(out + "\n");
        }
        log.flush();
    }

    //实现str
    //accdb  cdb
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        int pn = 0;
        while (pn < m - n + 1) {
            while (pn < m - n + 1 && haystack.charAt(pn) != needle.charAt(0)) pn++;
            int maxLen = 0;
            int pl = 0;
            while (pl < n && pn < m && haystack.charAt(pn) == needle.charAt(pl)) {
                maxLen++;
                pl++;
                pn++;
            }
            if (maxLen == n) return pn - n;
            //回溯
            pn = pn - maxLen + 1;
        }
        return -1;
    }

    //
    public List<List<String>> displayTable(List<List<String>> orders) {
        // 从订单中获取餐品名称和桌号，统计每桌点餐数量
        Set<String> nameSet = new HashSet<String>();
        Map<Integer, Map<String, Integer>> foodsCnt = new HashMap<Integer, Map<String, Integer>>();
        for (List<String> order : orders) {
            nameSet.add(order.get(2));
            int id = Integer.parseInt(order.get(1));
            Map<String, Integer> map = foodsCnt.getOrDefault(id, new HashMap<String, Integer>());
            map.put(order.get(2), map.getOrDefault(order.get(2), 0) + 1);
            foodsCnt.put(id, map);
        }

        // 提取餐品名称，并按字母顺序排列
        int n = nameSet.size();
        List<String> names = new ArrayList<String>();
        for (String name : nameSet) {
            names.add(name);
        }
        Collections.sort(names);

        // 提取桌号，并按餐桌桌号升序排列
        int m = foodsCnt.size();
        List<Integer> ids = new ArrayList<Integer>();
        for (int id : foodsCnt.keySet()) {
            ids.add(id);
        }
        Collections.sort(ids);

        // 填写点菜展示表
        List<List<String>> table = new ArrayList<List<String>>();
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : names) {
            header.add(name);
        }
        table.add(header);
        for (int i = 0; i < m; ++i) {
            int id = ids.get(i);
            Map<String, Integer> cnt = foodsCnt.get(id);
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(id));
            for (int j = 0; j < n; ++j) {
                row.add(Integer.toString(cnt.getOrDefault(names.get(j), 0)));
            }
            table.add(row);
        }
        return table;
    }

    //大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
    int mod = (int) 1e9 + 7;
    int max = 1 << 22;

    public int countPairs(int[] ds) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int x : ds) {
            for (int i = 1; i < max; i <<= 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    ans += map.get(t);
                    if (ans >= mod) ans -= mod;
                }
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return ans;
    }

}
