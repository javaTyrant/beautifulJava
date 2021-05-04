package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/5/26
 */
public class RestoreIpAddress {
    public static List<String> restoreIpAddresses(String s) {
        //结果
        List<String> ans = new ArrayList<>();
        //回溯
        back(s, ans, new ArrayList<>(), 0);
        return ans;
    }

    /**
     * @param s   数据源
     * @param ans 结果集
     * @param cur 当前的集合
     * @param pos 索引
     */
    private static void back(String s, List<String> ans, List<String> cur, int pos) {
        //如果当前的集合的数量大于4了
        if (cur.size() >= 4) {
            //s.length == pos
            if (s.length() == pos) {
                //找到一个ip,给ip加点
                ans.add(String.join(".", cur));
            }
            return;
        }
        //ip的长度是3
        for (int i = 1; i <= 3; i++) {
            //不能越界
            if (pos + i > s.length()) break;
            //当前的ip
            String seg = s.substring(pos, pos + i);
            //核心判断]
            //以0开头长度不能超过1,i==3不能大于255,剪枝
            if (seg.startsWith("0") && seg.length() > 1 || (i == 3 && Integer.parseInt(seg) > 255))
                continue;
            //add
            cur.add(seg);
            //pos + i
            back(s, ans, cur, pos + i);
            //回溯
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        //"010010"
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
