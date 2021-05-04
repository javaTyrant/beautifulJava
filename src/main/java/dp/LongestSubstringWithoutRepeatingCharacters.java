package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class LongestSubstringWithoutRepeatingCharacters {
    //无重复的最长子串
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int ans = 0;
        //map的value存放的是什么呢,位置信息,什么位置呢,start的下一个位置
        //牛逼
        Map<Character, Integer> map = new HashMap<>();
        //两个指针,start,end,变化end
        for (int start = 0, end = 0; end < len; end++) {
            char c = s.charAt(end);
            //如果重复了,更新start
            if (map.containsKey(c)) {
                //start,abca,如果map里有这个值了,start取得是下一个位置
                //abba这种情况最后到a的时候,我们要确保a在最大的位置
                //map.get(c)什么时候<start
                start = Math.max(map.get(c), start);
            }
            //结果,取最大的
            ans = Math.max(ans, end - start + 1);
            map.put(c, end + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstringOff(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针,rk初始值是-1,所以要+1
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    //不用hashmap,用数组,本质是一样的
    public static int lengthOfLongestSubstringBest(String s) {
        // 判断字符串的长度为0
        if (s.length() == 0) {
            return 0;
        }
        // 窗口大小
        int[] windows = new int[128];
        // 左右指针
        int left = 0;
        int right = 0;
        // 记录最长字串
        int maxSub = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            windows[ch]++;
            // 循环判断窗口中的ch的个数大于1，移动左指针
            while (windows[ch] > 1) {
                char dh = s.charAt(left);
                windows[dh]--;
                left++;
            }
            // 获取最大字串长度
            maxSub = Math.max(maxSub, right - left + 1);
            right++;
        }

        return maxSub;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstringBest("abcabccdea"));
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    //abcdabcde
    public int lengthOfLongestSubstringOwn(String s) {
        return 0;
    }
}
