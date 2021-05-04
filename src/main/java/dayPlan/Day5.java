package dayPlan;

import treenode.Tree;

import java.util.*;

/**
 * @author lumac
 * @since 2021/2/27
 */
public class Day5 implements Tree {
    public static void main(String[] args) {
        Day5 day5 = new Day5();
        System.out.println(day5.multiply("123", "456"));
        int[] arr = {3, 4, -1, 1, 9, -5};
        System.out.println(day5.firstMissingPositive(arr));
    }

    //缺失的第一个正数:没有被映射到,牛逼
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //当小于0的时候,重新赋值
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        //3 4 5 1
        for (int i = 0; i < n; i++) {
            //取正数
            int num = Math.abs(nums[i]);
            if (num <= n) {
                //
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        //把
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    //字符串相乘
    public String multiply(String num1, String num2) {
        int sumLen = num1.length() + num2.length();
        int[] res = new int[sumLen];
        //开始遍历
        for (int i = 0; i < num1.length(); i++) {
            //从右边开始取
            int num11 = num1.charAt(num1.length() - 1 - i) - '0';//3
            //同
            for (int j = 0; j < num2.length(); j++) {
                int num22 = num2.charAt(num2.length() - 1 - j) - '0';//6,5,4
                //res位置
                res[i + j] += num11 * num22;            //序列和相同相加
            }
        }
        //
        for (int i = 0; i < res.length - 1; i++) {
            if (res[i] >= 10) {
                res[i + 1] += res[i] / 10;//后位加上
                res[i] %= 10;//余数
            }
        }
        //
        int i = res.length - 1;
        while (i > 0 && res[i] == 0) {
            i--;
        } // 去除结果前面的 0
        StringBuilder sb = new StringBuilder();
        for (; i >= 0; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //937. 重新排列日志文件
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {//运用到了java里的泛型，第二个参数重新定义排序规则
            String[] split1 = log1.split(" ", 2); //将log1 按分隔符“ ” ，分成2份，即把标识符分开来
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));//判断除标识符外的第一个字符是数字true，字母false
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) { //如果两个日志都是字母日志
                int cmp = split1[1].compareTo(split2[1]); //先比较内容字母split1>split2则返回1，等于返0，小于返-1
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);//若内容字母相同则比较标识符
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            //如果split1是数字字符，且split2是数字字符返回0，
            // 如果split1是数字字符，且split2是字母字符返回1，即split1>split2,从小到大排序，split2提前
            // 如果split1是字母字符，返回-1，
        });
        return logs;
    }

    //662. 二叉树最大宽度 O(N)
    int ans;
    Map<Integer, Integer> left;

    public int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        left = new HashMap<>();
        dfs(root, 0, 0);
        return ans;
    }

    public void dfs(TreeNode root, int depth, int pos) {
        if (root == null) return;
        left.putIfAbsent(depth, pos);
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }

    //电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        //校验
        if (digits.length() == 0) {
            return combinations;
        }
        //字典
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations,
                          Map<Character, String> phoneMap,
                          String digits, int index,
                          StringBuffer combination) {
        //base case
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        //获取index数字
        char digit = digits.charAt(index);
        //获取对应的字母
        String letters = phoneMap.get(digit);
        //循环的长度
        int lettersCount = letters.length();
        //
        for (int i = 0; i < lettersCount; i++) {
            //append
            combination.append(letters.charAt(i));
            //index + 1
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            //delete
            combination.deleteCharAt(index);
        }
    }
}
