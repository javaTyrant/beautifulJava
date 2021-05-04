package dayPlan;

import java.util.*;

/**
 * @author lumac
 * @since 2021/4/18
 */
public class NewRound1 {
    //^:有一个为1就是1 .异或
    //~:取反.
    //&:都为1才是1
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }

//    public static void main(String[] args) {
//        int a = 1;//0001
//        int b = 2;//0010
//        int c = 3;//0011
//        System.out.println(a ^ b);//0011
//        System.out.println(b & c);//0010
//        System.out.println(~a);
//    }


    //
    public static int combinationSum4(int[] nums, int target) {
        //被之前的逻辑误导了呀.\
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    memo[i + num] += memo[i];
                }
            }
            System.out.println(i + ":" + Arrays.toString(memo));
        }
        return memo[target];
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return getImportanceHelper(map, id);
    }

    public int getImportanceHelper(Map<Integer, Employee> map, int id) {
        Employee employee = map.get(id);
        for (int subId : employee.subordinates) {
            employee.importance += getImportanceHelper(map, subId);
        }
        return employee.importance;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 2));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 1, 1));
        wall.add(Arrays.asList(2, 4));
        System.out.println(leastBricks(wall));
    }

    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (List<Integer> widths : wall) {
            int n = widths.size();
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += widths.get(i);
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            }
        }
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxCnt = Math.max(maxCnt, entry.getValue());
        }
        return wall.size() - maxCnt;
    }
}
